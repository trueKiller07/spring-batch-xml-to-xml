package com.trax.europeangateway.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.trax.europeangateway.model.dto.FileInformationHeaderDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtractHeaderOmegaXml  implements ExtractHeader<FileInformationHeaderDto>{
	
	public static final String FILE_INFORMATION_TAG = "fileInformation";
	public static final String SENDER_TAG = "sender";
	public static final String TIMESTAMP_TAG = "timestamp";
	public static final String ENVIRONMENT_TAG = "environment";
	public static final String VERSION_TAG = "version";

	@Override
	public FileInformationHeaderDto readHeader(String filePath) {
		
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		FileInformationHeaderDto header = new FileInformationHeaderDto();
		try {
			XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(filePath));
			while (xmlEventReader.hasNext()) {
				XMLEvent xmlEvent = xmlEventReader.nextEvent();
				 if (xmlEvent.isStartElement()){
	                   StartElement startElement = xmlEvent.asStartElement();
	                   switch (startElement.getName().getLocalPart()) {
	                   case FILE_INFORMATION_TAG: break;
	                   case SENDER_TAG: 
	                	   xmlEvent = xmlEventReader.nextEvent();
	                       header.setSender(xmlEvent.asCharacters().getData());
	                       break;
	                   case TIMESTAMP_TAG:
	                	   xmlEvent = xmlEventReader.nextEvent();
	                       header.setTimestamp(xmlEvent.asCharacters().getData());
	                       break;
	                   case ENVIRONMENT_TAG:
	                	   xmlEvent = xmlEventReader.nextEvent();
	                       header.setEnvironment(xmlEvent.asCharacters().getData());
	                       break;
	                   case VERSION_TAG:
	                	   xmlEvent = xmlEventReader.nextEvent();
	                       header.setVersion(xmlEvent.asCharacters().getData());
	                       break;
	                   default: 
	                	   log.debug("Could not find FileInformation header tag");
	                   }
	               }
	               if(xmlEvent.isEndElement()){
	                   EndElement endElement = xmlEvent.asEndElement();
	                   if(endElement.getName().getLocalPart().equals(FILE_INFORMATION_TAG)){
	                	   return header;
	                   }
	               }
			}
		} catch (FileNotFoundException | XMLStreamException e) {
			log.error("Exception ({}) occured while reading header information for file {}",e.getStackTrace()[0], filePath);
			e.printStackTrace();
		}
		return header;
	}
	

}
