package edu.ustc.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class GatewayServer {
	
	private static final Logger logger = LoggerFactory.getLogger(GatewayServer.class);
	
	public static void main(String[] args) throws Exception {
    	
		new SpringApplicationBuilder(GatewayServer.class).web(true).run(args);
    	
        logger.info("micro-service-netflix-gateway is running...");
    }
}
