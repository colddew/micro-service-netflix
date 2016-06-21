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
	
	public void invokeByNativeEurekaClient() {
		
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("micro-service-netflix-server", false);
		
		logger.info("##### {} #####", instance.getHomePageUrl());
	}
}
