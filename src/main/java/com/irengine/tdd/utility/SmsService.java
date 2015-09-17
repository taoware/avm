package com.irengine.tdd.utility;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SmsService {
	
	private static String baseUrl = "http://sdk999ws.eucp.b2m.cn:8080/sdkproxy/sendsms.action";
	private static String cdkey = "9SDK-EMY-0999-JDUNL";
	private static String password = "vencoosun";
	
	@Autowired
	private XmlService xmlService;
	
	public SmsResponse send(String mobile, String message) throws IOException {
			String url = String.format("%s?cdkey=%s&password=%s&phone=%s&message=%s", baseUrl, cdkey, password, mobile, message);
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(url, String.class).replaceAll("\r|\n", "");
			System.out.println(result);
			
			return (SmsResponse)xmlService.convertFromXMLToObject(result);			
	}


}

