package com.irengine.tdd.runner.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyRunner implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(DummyRunner.class);
	
    public void run(String... args) {
    	
    	log.info("Dummy Runner");

    }

}