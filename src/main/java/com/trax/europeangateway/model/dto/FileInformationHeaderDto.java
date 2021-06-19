package com.trax.europeangateway.model.dto;

import lombok.Data;

@Data
public class FileInformationHeaderDto {

	protected String sender;
	
    protected String timestamp;
    
    protected String environment;
    
    protected String version;
}
