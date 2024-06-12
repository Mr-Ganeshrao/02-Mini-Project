package in.ganesh.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ganesh.binding.LoginForm;
import in.ganesh.service.UserManagementServiceImp;

@RestController
public class LoginRestController {

	@Autowired
	private UserManagementServiceImp service;
	
	

	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {

		return service.login(loginForm);
	}

}
