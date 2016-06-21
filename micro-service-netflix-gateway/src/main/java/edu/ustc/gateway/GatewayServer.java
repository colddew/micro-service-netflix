package edu.ustc.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServer {
	
	private static final Logger logger = LoggerFactory.getLogger(GatewayServer.class);
	
	public static void main(String[] args) throws Exception {
    	
		SpringApplication.run(GatewayServer.class, args);
    	
        logger.info("micro-service-netflix-gateway is running...");
    }
}
