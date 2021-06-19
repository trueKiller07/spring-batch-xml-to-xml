package com.trax.europeangateway.model.dto;

import java.util.List;

import com.trax.europeangateway.model.dto.xsd.omega.TransactionPositionReport;

import lombok.Data;

@Data
public class ProcessorWriterDto {
	
	private List<EdsRequestDto> edsRequestDtoList;
	
	private List<EdsResponseDto> edsResponseDtoList;
	
	private TransactionPositionReport transaction;

}
