package edu.ustc.server.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import edu.ustc.common.dto.NetflixMicroService;

@RestController
@RequestMapping("/api/v1/server2")
@RefreshScope
public class MicroServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(MicroServiceController.class);
	
	@Value("${concurrent.quantity}")
	private String concurrentQuantity;
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String home() {
		
		logger.info("concurrentQuantity is {}", concurrentQuantity);
		
		logger.info("dynamic concurrentQuantity is {}", DynamicPropertyFactory.getInstance().getStringProperty("concurrent.quantity", "0").getValue());
		
        return "hello server2 !";
    }
	
	@RequestMapping(value = "/hystrixMethod2", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "defaultHystrixMethod", 
		groupKey = "micro-service-netflix-server2.MicroServiceGroup", 
		commandKey = "micro-service-netflix-server2.MicroServiceController.hystrixMethod", 
		threadPoolKey = "micro-service-netflix-server2.MicroServiceThreadPool",
		commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") 
	})
	public String hystrixMethod() {
//		logger.info("" + 1/0);
		return "hystrix method2";
	}
	
	public String defaultHystrixMethod() {
		return "defalut hystrix method2";
	}
	
	@RequestMapping(value = "/netflixMicroService2", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "defaultNetflixMicroService2", 
		groupKey = "micro-service-netflix-server2.MicroServiceGroup", 
		commandKey = "micro-service-netflix-server2.MicroServiceController.netflixMicroService2", 
		threadPoolKey = "micro-service-netflix-server2.MicroServiceThreadPool",
		commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") 
	})
	public NetflixMicroService netflixMicroService2() {
		return new NetflixMicroService(1, "micro-service-netflix-server2", "down", new Date());
	}
	
	public NetflixMicroService defaultNetflixMicroService2() {
		return null;
	}
}