package edu.ustc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.metrics.atlas.EnableAtlas;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableAtlas
@EnableScheduling
public class MicroServiceServer {
	
	private static final Logger logger = LoggerFactory.getLogger(MicroServiceServer.class);
	
	public static void main(String[] args) throws Exception {
    	
		new SpringApplicationBuilder(MicroServiceServer.class).web(true).run(args);
    	
        logger.info("micro-service-netflix-server is running...");
    }
}
