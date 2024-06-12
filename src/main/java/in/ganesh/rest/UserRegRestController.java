package in.ganesh.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ganesh.binding.UserForm;
import in.ganesh.service.UserManagementServiceImp;

@RestController
public class UserRegRestController {

	@Autowired
	private UserManagementServiceImp service;

	@GetMapping("/email/{emailId}")
	public String emailCheck(@PathVariable("emailId") String emailId) {

		return service.emailCheck(emailId);
	}

	@GetMapping("/countries")
	public Map<Integer, String> getCountries() {

		return service.loadCountries();
	}

	@GetMapping("/states/{countryID}")
	public Map<Integer, String> getStates(@PathVariable("countryId") Integer countryId) {

		return service.loadStates(countryId);

	}

	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getCities(@PathVariable("stateID") Integer stateId) {

		return service.loadCities(stateId);

	}

	@PostMapping("/user")
	public String userRegistration(@RequestBody UserForm userForm) {

		return service.registeruser(userForm);
	}

}
