package com.trax.europeangateway.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class EdsRequestDto {

	private String submissionAccountId;

	private String executingLei;

	private String naturalPersonType;

	private String correlationId;

	private List<PersonalInformation> personalInfo;

}
