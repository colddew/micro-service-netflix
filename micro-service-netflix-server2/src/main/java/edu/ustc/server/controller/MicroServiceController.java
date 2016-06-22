package edu.ustc.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

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
		
        return "hello server2 !";
    }
	
	@RequestMapping(value = {"/hystrixMethod2"}, method = RequestMethod.GET)
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
}