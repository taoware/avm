package com.irengine.tdd.utility;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.irengine.tdd.TddInPracticeApplication;
import com.irengine.tdd.runner.sample.DummyRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TddInPracticeApplication.class)
public class ApplicationContextHelperTest {
	
	@Test
	public void testGetContext() {
		DummyRunner runner = (DummyRunner)ApplicationContextHelper.getApplicationContext().getBean(DummyRunner.class);
		assertNotNull(runner);
	}

}
