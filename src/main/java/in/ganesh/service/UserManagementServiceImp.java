package in.ganesh.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ganesh.binding.LoginForm;
import in.ganesh.binding.UnlockForm;
import in.ganesh.binding.UserForm;
import in.ganesh.entity.CitiesMasterEntity;
import in.ganesh.entity.CountryMasterEntity;
import in.ganesh.entity.StateMasterEntity;
import in.ganesh.entity.UserMasterEntity;
import in.ganesh.repository.CityRepository;
import in.ganesh.repository.CountryRepository;
import in.ganesh.repository.StateRepository;
import in.ganesh.repository.UserAccountReposity;
import in.ganesh.util.EmailUtils;

@Service
public class UserManagementServiceImp implements UserManagementService {
	Strign h2;
	@Autowired
	private UserAccountReposity UserRepo;
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String login(LoginForm loginForm) {
		  UserMasterEntity entity = UserRepo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPwd());
		if (entity == null) {
			return "Invalid Credentials.";
		}
		if (entity != null && entity.getAccStatus().equals("LOCKED")) {
			return "Your Account Locked";
		}

		return "Login Success.....!";
	}

	@Override
	public String emailCheck(String emailId) {
		 UserMasterEntity entity = UserRepo.findByEmail(emailId);
		if (entity == null) {
			return "UNIQUE E-MAIL";
		} else {
			return "E-MAIl ALREADY EXIST";
		}
	}

	@Override
	public Map<Integer, String> loadCountries() {
		List<CountryMasterEntity> countries = countryRepo.findAll();

		Map<Integer, String> countryMap = new HashMap<>();

		for (CountryMasterEntity entity : countries) {

			countryMap.put(entity.getCountryId(), entity.getCountryName());
		}

		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStates(Integer countryId) {
		List<StateMasterEntity> states = stateRepo.findBycountryId(countryId);

		Map<Integer, String> statesMap = new HashMap<>();

		for (StateMasterEntity entity : states) {

			statesMap.put(entity.getCountryId(), entity.getStateName());
		}

		return statesMap;
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {
		List<CitiesMasterEntity> cities = cityRepo.findBystateId(stateId);

		Map<Integer, String> cityMap = new HashMap<>();

		for (CitiesMasterEntity entity : cities) {
			cityMap.put(entity.getCityID(), entity.getCityName());
		}
		return cityMap;
	}

	@Override
	public String registeruser(UserForm userForm) {
		UserMasterEntity entity = new UserMasterEntity();

		BeanUtils.copyProperties(userForm, entity);

		entity.setAccStatus("LOCKED");

		entity.setPassword(generateRandompwd());

		UserMasterEntity savedEntity = UserRepo.save(entity);

		// Logic To Send Email

		String email = userForm.getEmail();
		String subject = "User Registration process...";

		String fileName = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
		String body = readMailBodyContent(fileName, entity);

		boolean isSent = emailUtils.sendEmail(email, subject, body);

		if (savedEntity.getUserid() != null && isSent) {
			return "SUCCESS";
		}
		return "FAiLURE";
	}

	@Override
	public String unlockAccount(UnlockForm unlockForm) {

		if (!unlockForm.getNewPwd().equals(unlockForm.getConfirmNewPwd())) {
			return "Password And Confirm Password Should be  Same";
		}

		UserMasterEntity entity = UserRepo.findByEmailAndPassword(unlockForm.getEmail(), unlockForm.getTempPwd());
		if (entity == null) {
			return "Incorrect Temporary Password";
		}
		entity.setPassword(unlockForm.getNewPwd());
		entity.setAccStatus("Unlocked");

		// update record
		UserRepo.save(entity);

		return "Account Unlocked";
	}

	@Override
	public String forgetPwd(String emailId) {
		UserMasterEntity entity = UserRepo.findByEmail(emailId);

		if (entity == null) {
			return "Invalid E-mail";
		}

		// Email Sending
		String filename = "RECOVER-PASSWORD-EMAIL-BODY-TEMPLATE.txt";

		String BodyContent = readMailBodyContent(filename, entity);

		String subject = "Recover password - R industry";

		boolean isSent = emailUtils.sendEmail(filename, subject, BodyContent);

		if (isSent) {
			return "Password sent to registred email...";
		}
		return null;
	}

	
	
	// Auto password generation method
	private String generateRandompwd() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 6;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return generatedString;
	}

	
	
	
	private String readMailBodyContent(String fileName, UserMasterEntity entity) {
		String mailBody = null;
		try {
			StringBuffer sb = new StringBuffer();

			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine();// reading first line data

			while (line != null) {
				sb.append(line);// appending line data to obj
				line = br.readLine();// reading next line data
			}
			mailBody = sb.toString();

			mailBody = mailBody.replace("{FNAME}", entity.getFname());
			mailBody = mailBody.replace("{LNAME}", entity.getLname());
			mailBody = mailBody.replace("{Temp-pwd}", entity.getPassword());
			mailBody = mailBody.replace("{EMAIL}", entity.getEmail());
			mailBody = mailBody.replace("{pwd}", entity.getPassword());
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

}
