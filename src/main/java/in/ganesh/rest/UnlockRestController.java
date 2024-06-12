package in.ganesh.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ganesh.binding.UnlockForm;
import in.ganesh.service.UserManagementServiceImp;

@RestController
public class UnlockRestController {

	@Autowired
	private UserManagementServiceImp service;
	
	@PostMapping("/unlock")
	public String unlockAccount(@RequestBody UnlockForm unlockForm) {
		
		return service.unlockAccount(unlockForm);
	}
	
}
