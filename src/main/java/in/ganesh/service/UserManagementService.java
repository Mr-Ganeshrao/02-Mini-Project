package in.ganesh.service;

import java.util.Map;

import in.ganesh.binding.LoginForm;
import in.ganesh.binding.UnlockForm;
import in.ganesh.binding.UserForm;

public interface UserManagementService {
	String hi;
	//login Functionality Method
	public String login(LoginForm loginForm);
	
	//Registration Functionalities Method
	public String emailCheck(String emailId);
	

	public Map<Integer,String> loadCountries();
	
	public Map<Integer, String> loadStates(Integer countryId);
	
	public Map<Integer, String> loadCities(Integer stateId);
	 
	public String registeruser(UserForm userForm);
	
	//Unlock Account Functionality Methods
	public String unlockAccount(UnlockForm unlockForm);
	
	//Forgot password Funtionality Method
	public String forgetPwd(String emailId);

	
}
	
	
