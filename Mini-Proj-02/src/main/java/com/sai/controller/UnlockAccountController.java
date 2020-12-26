package com.sai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sai.model.UnlockAccountModel;
import com.sai.service.UserRegistrationService;

@RestController
public class UnlockAccountController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@PostMapping(value = "/unlockaccount", produces = "application/json", consumes = "application/json")
	public String unclockAccount(@RequestBody UnlockAccountModel unlockaccount) {
		String unlockAcc = userRegistrationService.unlockAccount(unlockaccount.getEmailId(),
				unlockaccount.getTempPassword(), unlockaccount.getNewPassword());
		return unlockAcc;
	}

	/*
	 * @GetMapping(value = "/unlockaccount/{emailId}", produces =
	 * "application/json") public String unlockAcc(@PathVariable("emailId") String
	 * emailId) {
	 * 
	 * }
	 */

}
