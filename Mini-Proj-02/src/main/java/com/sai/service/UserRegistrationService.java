package com.sai.service;

import java.util.Map;

import com.sai.model.UserRegistrationModel;

public interface UserRegistrationService {

	// Registration Page operations.

	public Map<Integer, String> getCounties();

	public Map<Integer, String> getStateByCountry(Integer countryId);

	public Map<Integer, String> getCityByState(Integer stateId);

	public boolean isEmailExist(String email);

	public boolean signup(UserRegistrationModel usermodel);

	// Login Operations

	public String signin(String email, String password);

	// Unlock account operations

	public String unlockAccount(String email, String tempPassword, String password);

	// Forgot password functionality

	public boolean forgotPassowrd(String email);

}
