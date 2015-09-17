package com.irengine.tdd.utility;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.irengine.tdd.TddInPracticeApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TddInPracticeApplication.class)
public class XmlServiceTest {
	
	@Autowired
	private XmlService xmlService;
	
	@Test
	public void testConvertFromXmlToObject() throws IOException {		
		String xml = "\r\n\r\n\r\n<?xml version=\"1.0\" encoding=\"UTF-8\"?><response><error>0</error><message></message></response>\r\n\r\n";
		xml = xml.replaceAll("\r|\n", "");
		System.out.println(xml);
		SmsResponse response = (SmsResponse) xmlService.convertFromXMLToObject(xml);
		assertEquals(0, response.getError());
	}

}
