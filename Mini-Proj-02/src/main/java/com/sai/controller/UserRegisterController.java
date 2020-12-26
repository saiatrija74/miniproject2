package com.sai.controller;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sai.model.UserRegistrationModel;
import com.sai.service.UserRegistrationService;

@RestController
@RequestMapping("/register")
public class UserRegisterController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@GetMapping("/isemailexist")
	public boolean isEmailExist(String email) {
		return userRegistrationService.isEmailExist(email);
	}

	@GetMapping(value = "/countries", produces = { "application/json" })
	public ResponseEntity<Map<Integer, String>> getContries() {
		Map<Integer, String> counties = userRegistrationService.getCounties();

		return new ResponseEntity<Map<Integer, String>>(counties, HttpStatus.OK);
	}

	@GetMapping(value = "/states/{countryId}", produces = { "application/json" })
	public ResponseEntity<Map<Integer, String>> getStates(@PathVariable("countryId") Integer countryId) {
		Map<Integer, String> stateByCountry = userRegistrationService.getStateByCountry(countryId);
		return new ResponseEntity<Map<Integer, String>>(stateByCountry, HttpStatus.OK);
	}

	@GetMapping(value = "/cities/{stateId}", produces = { "application/json" })
	public ResponseEntity<Map<Integer, String>> getCities(@PathVariable("stateId") Integer stateId) {
		Map<Integer, String> cityByState = userRegistrationService.getCityByState(stateId);
		return new ResponseEntity<Map<Integer, String>>(cityByState, HttpStatus.OK);
	}

	@PostMapping(value = "/user", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<String> registerUser(@RequestBody UserRegistrationModel userRegModel) {

		boolean signup = userRegistrationService.signup(userRegModel);
		if (signup)
			return new ResponseEntity<String>("User Registered succesfully", HttpStatus.CREATED);

		return new ResponseEntity<String>("User registration failed", HttpStatus.BAD_REQUEST);
	}

}
