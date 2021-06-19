package com.trax.europeangateway.model.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EdsRestProperty {

	@Value("${eds.rest.backoff}")
	private long backoffTime;
	
	@Value("${eds.rest.retry.count}")
	private int retryCount;
	
}
