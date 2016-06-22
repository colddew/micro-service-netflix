package edu.ustc.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardServer {
	
	private static final Logger logger = LoggerFactory.getLogger(HystrixDashboardServer.class);
	
	public static void main(String[] args) throws Exception {
    	
		SpringApplication.run(HystrixDashboardServer.class, args);
    	
        logger.info("micro-service-netflix-hystrix-dashboard is running...");
    }
}
