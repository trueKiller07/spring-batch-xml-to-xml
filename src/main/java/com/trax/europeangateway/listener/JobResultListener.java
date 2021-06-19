package com.trax.europeangateway.listener;

import java.time.Duration;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Component
public class JobResultListener implements JobExecutionListener {
 
    public void beforeJob(JobExecution jobExecution) {
    	log.info("Starting extractPersonalData Job for submission acount {}", jobExecution.getJobParameters().getString("submission.account"));
    	log.info("Job instance details: {}", jobExecution.getJobInstance().toString());
    }
 
    public void afterJob(JobExecution jobExecution) {
    	if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
    		log.info("ExtractPersonalData completed for submission account {} between time {} - {}. Job duration(seconds): {} ",
            		jobExecution.getJobParameters().getString("submission.account.id"), jobExecution.getCreateTime(), jobExecution.getEndTime(),
            		Duration.between(jobExecution.getCreateTime().toInstant(), jobExecution.getEndTime().toInstant()).abs().getSeconds());
    	} else {
    		log.error("ExtractPersonalData failed for submission account {}", jobExecution.getJobParameters().getString("submission.account"));
    	}
        
    }
}
