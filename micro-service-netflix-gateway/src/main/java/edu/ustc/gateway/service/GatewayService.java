package edu.ustc.gateway.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class GatewayService {
	
	private static final Logger logger = LoggerFactory.getLogger(GatewayService.class);
	
	@Autowired
	private EurekaClient discoveryClient;
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	public void invokeRemoteServiceByNativeEurekaClient() {
		
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("micro-service-netflix-server", false);
		logger.info("invoke remote service micro-service-netflix-server by native eureka client, ##### {} #####", instance.getHomePageUrl());
		
		InstanceInfo instance2 = discoveryClient.getNextServerFromEureka("micro-service-netflix-server2", false);
		logger.info("invoke remote service micro-service-netflix-server2 by native eureka client, ##### {} #####", instance2.getHomePageUrl());
	}
	
	@HystrixCommand(fallbackMethod = "defaultInvokeRemoteHystrixService", 
		groupKey = "micro-service-netflix-gateway.GatewayGroup", 
		commandKey = "micro-service-netflix-gateway.GatewayService.invokeRemoteHystrixService", 
		threadPoolKey = "micro-service-netflix-gateway.GatewayThreadPool", 	
		commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") 
	})
	public String invokeRemoteHystrixService() {
		
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("micro-service-netflix-server", false);
		logger.info("invoke remote service micro-service-netflix-server by native eureka client, ##### {} #####", instance.getHomePageUrl());
		
		InstanceInfo instance2 = discoveryClient.getNextServerFromEureka("micro-service-netflix-server2", false);
		logger.info("invoke remote service micro-service-netflix-server2 by native eureka client, ##### {} #####", instance2.getHomePageUrl());
		
		return "invokeRemoteHystrixService";
	}
	
	public String defaultInvokeRemoteHystrixService() {
		return "defalut invokeRemoteHystrixService";
	}
	
	public String invokeRemoteServiceByRibbon() {
		
		ServiceInstance instance = loadBalancer.choose("micro-service-netflix-server");
		URI storesUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
		
		return storesUri.toString();
	}
}
