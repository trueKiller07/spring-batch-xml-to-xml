package com.trax.europeangateway.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;

import com.trax.europeangateway.itemprocessor.omegaxml.PIExtractorItemProcessor;
import com.trax.europeangateway.itemprocessor.omegaxml.PIRemoverItemProcessor;
import com.trax.europeangateway.itemwriter.omegaxml.EdsClientItemWriter;
import com.trax.europeangateway.itemwriter.omegaxml.ExtendedStaxEventItemWriter;
import com.trax.europeangateway.itemwriter.omegaxml.OmegaXmlFileWriter;
import com.trax.europeangateway.itemwriter.omegaxml.OmegaXmlFooterCallBack;
import com.trax.europeangateway.itemwriter.omegaxml.OmegaXmlHeaderCallBack;
import com.trax.europeangateway.listener.JobResultListener;
import com.trax.europeangateway.listener.StepResultListener;
import com.trax.europeangateway.model.dto.FileInformationHeaderDto;
import com.trax.europeangateway.model.dto.ProcessorWriterDto;
import com.trax.europeangateway.model.dto.xsd.omega.TransactionPositionReport;
import com.trax.europeangateway.service.ExtractHeaderOmegaXml;
import com.trax.europeangateway.util.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	PIExtractorItemProcessor pIExtractorItemProcessor;
	
	@Autowired
	StepResultListener stepResultListener;
	
	@Autowired
    JobBuilderFactory jobBuilderFactory;
     
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    
    @Autowired
	private FileUtils fileUtils;
    
    @Qualifier("europeanGatewayJobExecutor")
    @Autowired
    private TaskExecutor taskExecutor;
    
    @Value( "${eugateway.batch.chunk.size}" )
    private int chunkSize;
    
    @Qualifier("euGatewayJobLauncher")
    @Bean
    public JobLauncher euGatewayJobLauncher(JobRepository jobRepository) throws Exception {
    	SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
    	jobLauncher.setJobRepository(jobRepository);
    	jobLauncher.setTaskExecutor(taskExecutor);
    	jobLauncher.afterPropertiesSet();
    	return jobLauncher;
    }
	
    @JobScope
    @Bean (name = "extractHeaderStep")
    public Step extractHeaderStep(StepBuilderFactory steps , @Value("#{jobParameters['file.path']}") String path) {
        return steps.get("extractHeaderStep")
                .tasklet((contribution, chunkContext) -> {
                	FileInformationHeaderDto fileInformation = new ExtractHeaderOmegaXml().readHeader(path);
                    ExecutionContext jobExecutionContext =  chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
                    jobExecutionContext.put("file.information", fileInformation);
                    return RepeatStatus.FINISHED;
                }).build();
    }
    
    @JobScope
	@Bean (name = "extractAndReplacePersonalDataStep")
	public Step jobStep(ItemStreamReader<TransactionPositionReport> reader,
			CompositeItemProcessor<TransactionPositionReport, 
			ProcessorWriterDto> processor,
			CompositeItemWriter<ProcessorWriterDto> writer,
			StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("extractAndReplacePersonalDataStep")
				.<TransactionPositionReport, ProcessorWriterDto>chunk(chunkSize)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.listener(stepResultListener)
				.build();
	}

    @Qualifier("omegaXmlJob")
	@Bean
	public Job extractPersonalDataJob(Step extractHeaderStep, Step extractAndReplacePersonalDataStep, JobResultListener jobListener,
			JobBuilderFactory jobBuilderFactory) {
		return jobBuilderFactory.get("extractAndReplacePersonalDataJob")
				.incrementer(new RunIdIncrementer())
				.start(extractHeaderStep)
				.next(extractAndReplacePersonalDataStep)
				.listener(jobListener)
				.build();
	}

	@Bean
	@StepScope
    public ItemStreamReader<TransactionPositionReport> itemReader(@Value("#{jobParameters['file.path']}") String path) {
        Jaxb2Marshaller transactionMarshaller = new Jaxb2Marshaller();
        transactionMarshaller.setMappedClass(TransactionPositionReport.class);
        transactionMarshaller.setPackagesToScan(ClassUtils.getPackageName(TransactionPositionReport.class));
        transactionMarshaller.setSupportJaxbElementClass(true);
        transactionMarshaller.setSupportDtd(true);
        log.debug("Generating StaxEventItemReader");
 
        return new StaxEventItemReaderBuilder<TransactionPositionReport>()
                .name("transactionPositionReport")
                .resource(new FileSystemResource(path))
                .addFragmentRootElements("transaction")
                .unmarshaller(transactionMarshaller)
                .build();
    }
	
	@Bean
	@JobScope
	OmegaXmlHeaderCallBack getOmegaXmlHeaderCallBack(@Value("#{jobExecutionContext['file.information']}") FileInformationHeaderDto fileInformation){
	    return new OmegaXmlHeaderCallBack(fileInformation);
	}
	
	@Bean
	OmegaXmlFooterCallBack getOmegaXmlFooterCallBack(){
	    return new OmegaXmlFooterCallBack();
	}
	
	@StepScope
	@Bean(name = "staxTransactionWriter")
    public StaxEventItemWriter<TransactionPositionReport> staxTransactionItemWriter(OmegaXmlHeaderCallBack omegaXmlHeaderCallBack, 
    		@Value("#{jobParameters['file.path']}") String path, @Value("#{jobParameters['submission.account']}") String submissionAccount) {
        Resource exportFileResource = new FileSystemResource(fileUtils.getOutputFilePath(path, submissionAccount));
 
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setSupportJaxbElementClass(true);
		marshaller.setClassesToBeBound(TransactionPositionReport.class);
		HashMap<String, String> rootElementAttribs = new HashMap<String, String>();
		rootElementAttribs.put("xmlns", "http://deutsche-boerse.com/DBRegHub");
		rootElementAttribs.put("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		rootElementAttribs.put("xsi:schemaLocation", "http://deutsche-boerse.com/DBRegHub regulatoryHubUpload_MiFIR_001.60.xsd");
		
         ExtendedStaxEventItemWriter<TransactionPositionReport> writer = new ExtendedStaxEventItemWriter<TransactionPositionReport>();
         writer.setName("transactionWriter");
         writer.setVersion("1.0");
         writer.setResource(exportFileResource);
         writer.setMarshaller(marshaller);
         writer.setRootTagName("reportFile");
         writer.setRootElementAttributes(rootElementAttribs);
         writer.setHeaderCallback(omegaXmlHeaderCallBack);
         writer.setFooterCallback(getOmegaXmlFooterCallBack());
         writer.setShouldDeleteIfEmpty(true);
         writer.setIndenting(true);
         return writer;
    }
	
	@StepScope
	@Bean
    public PIExtractorItemProcessor extractItemProcessor() {
		log.debug("Generating PIExtractorItemProcessor");
    	return new PIExtractorItemProcessor();
    }
	
	@Bean
    public PIRemoverItemProcessor removeItemProcessor() {
		log.debug("Generating PIRemoverItemProcessor");
    	return new PIRemoverItemProcessor();
    }
	
	@Bean
	@StepScope
	CompositeItemProcessor<TransactionPositionReport, ProcessorWriterDto> extractAndRemoveItemProcessor() {
		log.debug("Generating CompositeItemProcessor");
		CompositeItemProcessor<TransactionPositionReport, ProcessorWriterDto> itemProcessor = new CompositeItemProcessor<>();
		itemProcessor.setDelegates((List<? extends ItemProcessor<?, ?>>) Arrays.asList(extractItemProcessor(), removeItemProcessor()));
		return itemProcessor;
	}
	
	@Bean
	public EdsClientItemWriter<ProcessorWriterDto> edsClientItemWriter() {
		log.debug("Generating EdsClientItemWriter");
		return new EdsClientItemWriter<>();
	}
	
	@Bean
	@StepScope
	public OmegaXmlFileWriter<ProcessorWriterDto> omegaXmlFileWriter(OmegaXmlHeaderCallBack omegaXmlHeaderCallBack, @Value("#{jobParameters['file.path']}") String path, @Value("#{jobParameters['submission.account']}") String submissionAccount) {
		log.debug("Generating OmegaXmlFileWriter");
		return new OmegaXmlFileWriter(staxTransactionItemWriter(omegaXmlHeaderCallBack, path, submissionAccount));
	}
	
	
	@Bean
	@StepScope
	public CompositeItemWriter<ProcessorWriterDto> compositeItemWriter(OmegaXmlHeaderCallBack omegaXmlHeaderCallBack, @Value("#{jobParameters['file.path']}") String path, @Value("#{jobParameters['submission.account']}") String submissionAccount) {
		log.debug("Generating CompositeItemWriter");
	    CompositeItemWriter<ProcessorWriterDto> compositeItemWriter = new CompositeItemWriter<>();
	    compositeItemWriter.setDelegates(Arrays.asList(edsClientItemWriter(), omegaXmlFileWriter(omegaXmlHeaderCallBack, path, submissionAccount)));
	    return compositeItemWriter;
	}
}
