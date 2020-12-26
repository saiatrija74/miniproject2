package com.sai.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Table(name = "USER_REG_DATA", uniqueConstraints = @UniqueConstraint(columnNames = "EMAIL"))
@Entity
@Data
public class UserRegistrationEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "RED_ID")
	private Integer regId;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	@Column(name = "DOB")
	private Date dateOfBirth;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "COUNTRY")
	private Integer country;
	@Column(name = "STATE")
	private Integer state;
	@Column(name = "CITY")
	private Integer city;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "ACCOOUNT_STATUS")
	private String acc_Status;

}
