package com.trax.europeangateway.itemwriter.omegaxml;

import java.io.Writer;

import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import org.springframework.batch.item.xml.StaxEventItemWriter;

import javanet.staxutils.IndentingXMLEventWriter;

public class ExtendedStaxEventItemWriter<T> extends StaxEventItemWriter<T> {

	private boolean indenting;
	
	public void setIndenting(boolean indenting) {
		this.indenting = indenting;
	}

	public boolean isIndenting() {
		return indenting;
	}

	@Override
	protected XMLEventWriter createXmlEventWriter(XMLOutputFactory outputFactory, Writer writer)
			throws XMLStreamException {
		outputFactory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
		if (isIndenting()) {
			return new IndentingXMLEventWriter(super.createXmlEventWriter(outputFactory, writer));
		} else {
			return super.createXmlEventWriter(outputFactory, writer);
		}
	}

}