package in.ganesh.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.ganesh.service.UserManagementServiceImp;

@RestController
public class ForgotPasswordController {

	@Autowired
	private UserManagementServiceImp service;

	
	@GetMapping("/forgotpwd/{email}")
	public String forgotPwd(@PathVariable("email") String email) {
		
		return service.forgetPwd(email);
		
	}
}
