package com.irengine.tdd.utility;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Component;

@Component
public class XmlService {
	
	@Autowired
	@Qualifier("marshaller")
	private Marshaller marshaller;
	@Autowired
	@Qualifier("marshaller")
	private Unmarshaller unmarshaller;

	public String convertFromObjectToXML(Object object) throws XmlMappingException, IOException {
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		marshaller.marshal(object, result);
		
		return writer.toString();
	}

	public Object convertFromXMLToObject(String xml) throws IOException {
		StringReader reader = new StringReader(xml);
		StreamSource source = new StreamSource(reader);

		return unmarshaller.unmarshal(source);
	}
}
