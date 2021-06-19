package com.trax.europeangateway.itemwriter.omegaxml;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;

import org.springframework.batch.item.xml.StaxWriterCallback;

import com.trax.europeangateway.exceptions.OmegaXmlHeaderWriterException;
import com.trax.europeangateway.model.dto.FileInformationHeaderDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmegaXmlHeaderCallBack implements StaxWriterCallback {
	
	FileInformationHeaderDto header;
	
	public OmegaXmlHeaderCallBack(FileInformationHeaderDto header) {
		super();
		this.header = header;
	}

    @Override
    public void write(XMLEventWriter writer) {
        XMLEventFactory factory = XMLEventFactory.newInstance();
        try {
            writer.add(factory.createStartElement("", "", "fileInformation"));

            writer.add(factory.createStartElement("", "", "sender"));
            writer.add(factory.createCharacters(header.getSender()));
            writer.add(factory.createEndElement("", "", "sender"));


            writer.add(factory.createStartElement("", "", "timestamp"));
            writer.add(factory.createCharacters(header.getTimestamp()));
            writer.add(factory.createEndElement("", "", "timestamp"));

            writer.add(factory.createStartElement("", "", "environment"));
            writer.add(factory.createCharacters(header.getEnvironment()));
            writer.add(factory.createEndElement("", "", "environment"));

            writer.add(factory.createStartElement("", "", "version"));
            writer.add(factory.createCharacters(header.getVersion()));
            writer.add(factory.createEndElement("", "", "version"));
            
            writer.add(factory.createEndElement("", "", "fileInformation"));
            
            writer.add(factory.createStartElement("", "", "record"));
            
        } catch (XMLStreamException e) {
            log.error("Error writing OMEGA XML Header: {}", e.getMessage());
            throw new OmegaXmlHeaderWriterException(e.getMessage());
        }
    }
}