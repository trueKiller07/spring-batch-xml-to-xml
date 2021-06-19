package com.trax.europeangateway.itemprocessor.omegaxml;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.trax.europeangateway.model.dto.ProcessorWriterDto;
import com.trax.europeangateway.model.dto.xsd.omega.TransactionPositionReport;
import com.trax.europeangateway.service.PersonalDataRemover;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PIRemoverItemProcessor implements ItemProcessor<ProcessorWriterDto, ProcessorWriterDto> {

	@Autowired
	public PersonalDataRemover personalDataRemover;
	@Override
	public ProcessorWriterDto process(ProcessorWriterDto item) throws Exception {
		TransactionPositionReport transaction = item.getTransaction();
		if(transaction == null) 
			return item;
		log.info("Removing personal data for transaction customer id {} and create EDS requestDto.", item.getTransaction().getProcessingDetails().getCustomerTransactionId());
		personalDataRemover.removePIData(transaction);
		return item;
	}

}
