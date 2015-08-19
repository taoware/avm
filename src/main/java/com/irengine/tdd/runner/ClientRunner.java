package com.irengine.tdd.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.irengine.tdd.network.SimpleClient;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class ClientRunner implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(ClientRunner.class);
	
    public void run(String... args) {
    	
    	log.info("Vending Client Runner");
        new SimpleClient("Vending Client", 7788).start();

    }

}