package com.trax.europeangateway.model.dto;

import lombok.Data;

@Data
public class PersonalInformation {

	private String countryOfNatioality;

	private String passportNumber;

	private String nationalId;

	private String concat;

	private String firstName;

	private String surName;

	/**
	 * DATE OF BIRTH
	 */
	private String dob;

	/**
	 * COUNTRY OF BRANCH
	 */
	private String countryOfBranch;

	private String dercon;

}
