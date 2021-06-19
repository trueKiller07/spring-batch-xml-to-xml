package com.trax.europeangateway.itemwriter.omegaxml;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;

import org.springframework.batch.item.xml.StaxWriterCallback;

import com.trax.europeangateway.exceptions.OmegaXmlHeaderWriterException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmegaXmlFooterCallBack implements StaxWriterCallback {

    @Override
    public void write(XMLEventWriter writer) {
        XMLEventFactory factory = XMLEventFactory.newInstance();
        try {
            writer.add(factory.createEndElement("", "", "record"));
        } catch (XMLStreamException e) {
            log.error("Error writing OMEGA XML Footer: {}", e.getMessage());
            throw new OmegaXmlHeaderWriterException(e.getMessage());
        }
    }
}