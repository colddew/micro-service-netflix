package edu.ustc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
	
	private static final Logger logger = LoggerFactory.getLogger(EurekaServer.class);
	
	public static void main(String[] args) throws Exception {
    	
		new SpringApplicationBuilder(EurekaServer.class).web(true).run(args);
    	
        logger.info("micro-service-netflix-eureka-server is running...");
    }
}
