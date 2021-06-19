package com.trax.europeangateway.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trax.europeangateway.util.FileUtils;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Component
public class StepResultListener implements StepExecutionListener {
	
	@Autowired
	FileUtils fileUtils;
 
    @Override
    public void beforeStep(StepExecution stepExecution) {
    	String submitter = stepExecution.getJobParameters().getString("submission.account");
		log.debug("StepResultListener called beforeStep for submitter {}", submitter);
    }
 
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
    	String filePath = stepExecution.getJobParameters().getString("file.path");
		String submitter = stepExecution.getJobParameters().getString("submission.account");
		log.debug("StepResultListener called afterStep for submitter {}", submitter);
		
    	if(stepExecution.getStatus() == BatchStatus.COMPLETED) {
    		return ExitStatus.COMPLETED;
    	} else {
    		return ExitStatus.FAILED;
    	}
    }
}