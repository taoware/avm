package com.irengine.tdd.runner;

import com.irengine.tdd.network.SimpleServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServerRunner implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(ServerRunner.class);
	
    public void run(String... args) {

        log.info("Vending Server Runner");
    	new SimpleServer("Vending Server", 7788).start();

    }

}