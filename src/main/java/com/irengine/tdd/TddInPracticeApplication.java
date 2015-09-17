package com.irengine.tdd;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@SpringBootApplication
public class TddInPracticeApplication {

	private static final Logger log = LoggerFactory.getLogger(TddInPracticeApplication.class);
	
	// listen ApplicationEvent which is spring event.
	@Bean
	public ApplicationListener<ApplicationEvent> applicationListener() {
		
		return new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {

				printEventInfo(event);
				
			}
		};
		
	}

	@Bean
	public ApplicationListener<ContextRefreshedEvent> applicationListenerForStarted() {
		
		return new ApplicationListener<ContextRefreshedEvent>() {
			@Override
			public void onApplicationEvent(ContextRefreshedEvent event) {

				printEventInfo(event);
				
			}
		};
		
	}

	@Bean
	public ApplicationListener<ContextClosedEvent> applicationListenerForStopped() {
		
		return new ApplicationListener<ContextClosedEvent>() {
			@Override
			public void onApplicationEvent(ContextClosedEvent event) {

				printEventInfo(event);
				
			}
		};
		
	}
	
	private static void printEventInfo(ApplicationEvent event) {
		
    	log.info("[" + event.getClass().getSimpleName() + "] from " + event.getSource());
    	
	}
	
	@Bean
	Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		  jaxb2Marshaller.setPackagesToScan("com.irengine.tdd");
		  Map<String,Object> map = new HashMap<String,Object>();
		  map.put("jaxb.formatted.output", true);
		  jaxb2Marshaller.setMarshallerProperties(map);
	      return jaxb2Marshaller;
	}
	

    public static void main(String[] args) {
    	
    	// listen SpringApplicationEvent which is spring boot event and triggered before bean created.
    	SpringApplication springApplication = new SpringApplication(TddInPracticeApplication.class);
    	springApplication.addListeners(new ApplicationListener<SpringApplicationEvent>() {

            @Override
            public void onApplicationEvent(SpringApplicationEvent event) {

                printEventInfo(event);

            }

        });
    	springApplication.run(args);
    	
    	// wait key-in to exit

        System.out.println("Press any key to exit...");
    	Scanner sc = new Scanner(System.in);
    	sc.nextLine();
        System.out.println("Exiting...");
    	sc.close();

    }
}
