package com.trax.europeangateway.itemwriter.omegaxml;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.trax.europeangateway.model.dto.ProcessorWriterDto;
import com.trax.europeangateway.model.dto.xsd.omega.TransactionPositionReport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmegaXmlFileWriter <T> implements ItemWriter<T>, ItemStream, InitializingBean  {
    
	StaxEventItemWriter<TransactionPositionReport> delegateWriter;
	
	public OmegaXmlFileWriter(StaxEventItemWriter<TransactionPositionReport> delegateWriter) {
		this.delegateWriter = delegateWriter;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends T> items) throws Exception {

		log.info("Item Writer for generating de-enriched file.");
		
		List<ProcessorWriterDto> processorWriterDtos = (List<ProcessorWriterDto>) items;
		List<TransactionPositionReport> transactions = processorWriterDtos.stream()
				.map(ProcessorWriterDto::getTransaction).collect(Collectors.toList());
		log.info("Delegating the write operation to StaxEventItemWriter");
		this.delegateWriter.write(transactions);
	} 
	
	@Override
    public void afterPropertiesSet() {
        Assert.notNull(delegateWriter, "You must set a delegate!");
    }

	@Override
    public void open(ExecutionContext executionContext) {
        if (delegateWriter instanceof ItemStream) {
            ((ItemStream) delegateWriter).open(executionContext);
        }
    }

	@Override
    public void update(ExecutionContext executionContext) {
        if (delegateWriter instanceof ItemStream) {
            ((ItemStream) delegateWriter).update(executionContext);
        }
    }

	@Override
    public void close() {
        if (delegateWriter instanceof ItemStream) {
            ((ItemStream) delegateWriter).close();
        }
    }

    public void setDelegate(StaxEventItemWriter<TransactionPositionReport> delegate) {
        this.delegateWriter = delegate;
    }
}
