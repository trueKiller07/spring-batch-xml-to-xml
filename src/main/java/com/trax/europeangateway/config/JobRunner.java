package com.trax.europeangateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class JobRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobRunner.class);

    private final Job omegaXmlJob;
    private final JobLauncher euGatewayJobLauncher;
    
    @Autowired
	public JobRunner(@Qualifier("omegaXmlJob") Job omegaXmlJob, @Qualifier("euGatewayJobLauncher") JobLauncher euGatewayJobLauncher) {
        this.omegaXmlJob = omegaXmlJob;
        this.euGatewayJobLauncher = euGatewayJobLauncher;
    }
    
	public void startPersonalDataExtractorJob(String submissionAccount, String filePath)
			throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException {
		LOGGER.info("Launching job for submissionAccount {} with input file {}", submissionAccount, filePath);
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("currentTime", String.valueOf(System.currentTimeMillis()))
				.addString("submission.account", submissionAccount)
				.addString("file.path", filePath)
				.toJobParameters();
		euGatewayJobLauncher.run(omegaXmlJob, jobParameters);
	}
}
