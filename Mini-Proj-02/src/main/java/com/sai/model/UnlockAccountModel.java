package com.sai.model;

import lombok.Data;

@Data
public class UnlockAccountModel {

	private String emailId;
	private String tempPassword;
	private String newPassword;

}
