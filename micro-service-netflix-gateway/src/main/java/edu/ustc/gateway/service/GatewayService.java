package edu.ustc.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Service
public class GatewayService {
	
	private static final Logger logger = LoggerFactory.getLogger(GatewayService.class);
	
	@Autowired
	private EurekaClient discoveryClient;
	
	public void invokeRemoteServiceByNativeEurekaClient() {
		
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("micro-service-netflix-server", false);
		logger.info("invoke remote service micro-service-netflix-server by native eureka client, ##### {} #####", instance.getHomePageUrl());
		
		InstanceInfo instance2 = discoveryClient.getNextServerFromEureka("micro-service-netflix-server2", false);
		logger.info("invoke remote service micro-service-netflix-server2 by native eureka client, ##### {} #####", instance2.getHomePageUrl());
	}
}
