package com.trax.europeangateway.itemprocessor.omegaxml;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.trax.europeangateway.model.dto.ProcessorWriterDto;
import com.trax.europeangateway.model.dto.xsd.omega.TransactionPositionReport;
import com.trax.europeangateway.service.PersonalDataExtractor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PIExtractorItemProcessor implements ItemProcessor<TransactionPositionReport, ProcessorWriterDto> {

	@Autowired
	PersonalDataExtractor personalDataExtractor;
	
	@Value("#{jobParameters['submission.account']}") 
	private String subAccntId;
	
	@Value("#{stepExecution}")
	private StepExecution stepExecution;
	
	@Override
	public ProcessorWriterDto process(TransactionPositionReport transaction) throws Exception {

		log.info("Extracting personal data for transaction customer id {} and create EDS requestDto.",
				transaction.getProcessingDetails().getCustomerTransactionId());
		ProcessorWriterDto transferObject = new ProcessorWriterDto();
		transferObject.setEdsRequestDtoList(personalDataExtractor.extract(transaction, subAccntId));
		transferObject.setTransaction(transaction);
		return transferObject;

	}
	
}
