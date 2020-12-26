package com.sai.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sai.entity.CityList;
import com.sai.entity.CountryList;
import com.sai.entity.StatesList;
import com.sai.entity.UserRegistrationEntity;
import com.sai.model.UserRegistrationModel;
import com.sai.repository.CityListRepo;
import com.sai.repository.CountryListRepo;
import com.sai.repository.StatesListRepo;
import com.sai.repository.UserRegistrationRepository;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	private UserRegistrationRepository userRegistrationrepo;

	private CityListRepo cityListRepo;

	private CountryListRepo countryListRepo;

	private StatesListRepo statesListRepo;

	@Autowired
	public UserRegistrationServiceImpl(UserRegistrationRepository userRegistrationrepo, CityListRepo cityListRepo,
			CountryListRepo countryListRepo, StatesListRepo statesListRepo) {
		this.userRegistrationrepo = userRegistrationrepo;
		this.cityListRepo = cityListRepo;
		this.countryListRepo = countryListRepo;
		this.statesListRepo = statesListRepo;

	}

	@Override
	public Map<Integer, String> getCounties() {
		List<CountryList> findAll = countryListRepo.findAll();
		Map<Integer, String> countries = new HashMap<Integer, String>();
		findAll.forEach(country -> {
			countries.put(country.getCountryId(), country.getCountry());
		});
		return countries;
	}

	@Override
	public Map<Integer, String> getStateByCountry(Integer countryId) {
		Map<Integer, String> stateMap = new HashMap<Integer, String>();
		List<StatesList> findByCountryId = statesListRepo.findByCountryId(countryId);
		findByCountryId.forEach(state -> {
			stateMap.put(state.getStateId(), state.getState());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCityByState(Integer stateId) {
		Map<Integer, String> cityMap = new HashMap<Integer, String>();
		List<CityList> findByStateId = cityListRepo.findByStateId(stateId);
		findByStateId.forEach(city -> {
			cityMap.put(city.getCityId(), city.getCity());
		});
		return cityMap;
	}

	@Override
	public boolean isEmailExist(String email) {

		Optional<UserRegistrationEntity> findByEmail = userRegistrationrepo.findByEmail(email);
		if (findByEmail.isPresent()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean signup(UserRegistrationModel usermodel) {
		UserRegistrationEntity entity = new UserRegistrationEntity();
		BeanUtils.copyProperties(usermodel, entity);
		entity.setAcc_Status("N");
		entity.setPassword(getTempPassword());
		UserRegistrationEntity save = userRegistrationrepo.save(entity);
		if (save.getRegId() > 0) {
			return true;
		}
		return false;
	}

	// Temp password generation
	private String getTempPassword() {
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 6) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	@Override
	public String signin(String email, String password) {
		Optional<UserRegistrationEntity> findByEmail = userRegistrationrepo.findByEmail(email);
		String msg = "";
		if (findByEmail.isPresent()) {
			if (findByEmail.get().getAcc_Status() == "N")
				msg = "Email id is not activated yet!!!";
			else if ((findByEmail.get().getEmail().equalsIgnoreCase(email)
					&& findByEmail.get().getPassword().equals(password))) {
				return "homepage";
			} else {
				msg = "Credentials are in correct";
			}
		} else {
			msg = "Email id is not registered";
		}

		return msg;
	}

	@Override
	public String unlockAccount(String email, String tempPassword, String newpassword) {
		String msg = "";
		Optional<UserRegistrationEntity> findByEmail = userRegistrationrepo.findByEmail(email);
		if (findByEmail.get().getPassword().equals(tempPassword)) {
			if (findByEmail.get().getAcc_Status() == "N") {
				UserRegistrationEntity userRegistrationEntity = findByEmail.get();
				userRegistrationEntity.setAcc_Status("Y");
				userRegistrationEntity.setPassword(newpassword);
				userRegistrationrepo.save(userRegistrationEntity);
				msg = "Account is Unlocked";
			} else {
				msg = "Account is already unlocked";
			}
		} else {
			msg = "Temprary password is not correct";
		}
		return msg;
	}

	@Override
	public boolean forgotPassowrd(String email) {
		Optional<UserRegistrationEntity> findByEmail = userRegistrationrepo.findByEmail(email);
		if (findByEmail.isPresent()) {

			return true;
		}
		return false;
	}

}
