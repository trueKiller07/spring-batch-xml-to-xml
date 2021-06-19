package com.trax.europeangateway.integration.batch;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.trax.europeangateway.EuropeangatewayApplication;
import com.trax.europeangateway.config.BatchConfiguration;
import com.trax.europeangateway.listener.StepResultListener;
import com.trax.europeangateway.util.EuGatewayConstants;
import com.trax.europeangateway.util.FileUtils;

@RunWith(SpringRunner.class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = { BatchConfiguration.class, StepResultListener.class, FileUtils.class, EuropeangatewayApplication.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, 
  DirtiesContextTestExecutionListener.class})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@TestPropertySource("classpath:application.properties")
public class SpringBatchIntegrationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
  
    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;
    
    @Value("${europeangateway.outbound.path}")
	private String outboundBasePath;
    
    @After
    public void cleanUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    private JobParameters defaultJobParameters() {
        JobParametersBuilder paramsBuilder = new JobParametersBuilder();
        paramsBuilder.addString("submission.account", "12345");
        paramsBuilder.addDate("currentTime", new Date());
        paramsBuilder.addString("file.path", System.getProperty("user.dir")+"\\src\\test\\resources\\data\\PRO_222100GH7XKDGO3GLZ60_20201231T090534024_GBXXXXXXT.XML");
        return paramsBuilder.toJobParameters();
   }
    
    @Test
    public void givenReferenceOutput_whenJobExecuted_thenSuccess() throws Exception {
        // given
        FileSystemResource expectedResult = new FileSystemResource(System.getProperty("user.dir")+"\\src\\test\\resources\\data\\PRO_222100GH7XKDGO3GLZ60_20201231T090534024_GBXXXXXXT_EXPECTED_OUTFILE.XML");
        FileSystemResource actualResult = new FileSystemResource(getActualFile("PRO_222100GH7XKDGO3GLZ60_20201231T090534024_GBXXXXXXT.XML"));

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(defaultJobParameters());
        try {
        	Thread.sleep(2000);
        }catch(Exception e) {}
        JobInstance actualJobInstance = jobExecution.getJobInstance();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();
      
        // then
        assertThat(actualJobInstance.getJobName(), is("extractAndReplacePersonalDataJob"));
        assertThat(actualJobExitStatus.getExitCode(), is("COMPLETED"));
        AssertFile.assertFileEquals(expectedResult, actualResult);
    }
    
    private String getActualFile(String fileName) {
    	StringBuilder outPath = new StringBuilder(outboundBasePath);
		outPath.append(EuGatewayConstants.FORWARD_SLASH);
		outPath.append("TestOut");
		outPath.append(EuGatewayConstants.FORWARD_SLASH);
		outPath.append(fileName);
		return outPath.toString();
    }
    
}	