package edu.ustc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
public class MicroServiceServer {
	
	private static final Logger logger = LoggerFactory.getLogger(MicroServiceServer.class);
	
	public static void main(String[] args) throws Exception {
    	
		SpringApplication.run(MicroServiceServer.class, args);
    	
        logger.info("micro-service-netflix-server is running...");
    }
}
