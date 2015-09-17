package com.irengine.tdd.utility;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.irengine.tdd.TddInPracticeApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TddInPracticeApplication.class)
public class SmsServiceTest {
	
	@Autowired
	private SmsService smsService;
	
	@Test
	public void testSend() throws IOException {
		SmsResponse response = (SmsResponse)smsService.send("13601711251", "正在发送消息！");
		assertNotNull(response);
	}

}
