package edu.ustc.gateway.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.ustc.common.dto.NetflixMicroService;

@FeignClient(name = "micro-service-netflix-server")
public interface MicroServiceNetflixServer {
	
	@RequestMapping(value = "/api/v1/server/hystrixMethod", method = RequestMethod.GET)
	String hystrixMethod();
	
	@RequestMapping(value = "/api/v1/server/netflixMicroService", method = RequestMethod.GET, consumes = "application/json")
	public NetflixMicroService netflixMicroService();
}
