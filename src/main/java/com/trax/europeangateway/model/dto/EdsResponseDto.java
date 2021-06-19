package com.trax.europeangateway.model.dto;

import lombok.Data;

@Data
public class EdsResponseDto {

	private String submissionAccountId;

	private String executingLei;

	private String correlationId;

	private String instructionId;

	private String token;

	private String countryOfBranch;

	private String tokenValidIndicator;

}
