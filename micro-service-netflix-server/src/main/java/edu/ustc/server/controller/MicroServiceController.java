package edu.ustc.server.controller;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import edu.ustc.common.dto.NetflixMicroService;
import edu.ustc.server.utils.OkHttpUtils;

@RestController
@RequestMapping("/api/v1/server")
//@RefreshScope
public class MicroServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(MicroServiceController.class);
	
	@Value("${concurrent.quantity}")
	private String concurrentQuantity;
	
//	@Scheduled(cron = "0/10 * * * * ?")
	public void refresh() throws Exception {
		OkHttpUtils.synPostForm("http://localhost:8080/refresh", new HashMap<>());
	}
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String home() {
		
		logger.info("concurrentQuantity is {}", concurrentQuantity);
		
		logger.info("dynamic concurrentQuantity is {}", DynamicPropertyFactory.getInstance().getStringProperty("concurrent.quantity", "0").getValue());
		
        return "hello server !";
    }
	
	@RequestMapping(value = "/hystrixMethod", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "defaultHystrixMethod", 
		groupKey = "micro-service-netflix-server.MicroServiceGroup", 
		commandKey = "micro-service-netflix-server.MicroServiceController.hystrixMethod", 
		threadPoolKey = "micro-service-netflix-server.MicroServiceThreadPool", 
		commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") 
	})
	public String hystrixMethod() {
		return "hystrix method";
	}
	
	public String defaultHystrixMethod() {
		return "defalut hystrix method";
	}
	
	@RequestMapping(value = "/netflixMicroService", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "defaultNetflixMicroService", 
		groupKey = "micro-service-netflix-server.MicroServiceGroup", 
		commandKey = "micro-service-netflix-server.MicroServiceController.netflixMicroService", 
		threadPoolKey = "micro-service-netflix-server.MicroServiceThreadPool", 
		commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") 
	})
	public NetflixMicroService netflixMicroService() {
		return new NetflixMicroService(1, "micro-service-netflix-server", "up", new Date());
	}
	
	public NetflixMicroService defaultNetflixMicroService() {
		return null;
	}
}