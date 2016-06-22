package edu.ustc.turbine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class TurbineServer {
	
	private static final Logger logger = LoggerFactory.getLogger(TurbineServer.class);
	
	public static void main(String[] args) throws Exception {
    	
		new SpringApplicationBuilder(TurbineServer.class).web(true).run(args);
    	
        logger.info("micro-service-netflix-turbine is running...");
    }
}
