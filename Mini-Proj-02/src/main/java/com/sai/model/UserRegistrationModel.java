package com.sai.model;

import java.util.Date;

import lombok.Data;

@Data
public class UserRegistrationModel {

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	private Date dateOfBirth;

	private String gender;

	private Integer country;

	private Integer state;

	private Integer city;

}
