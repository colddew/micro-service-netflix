package edu.ustc.gateway.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.ustc.common.dto.NetflixMicroService;

@FeignClient(name = "micro-service-netflix-server")
@RequestMapping("/api/v1/server")
public interface MicroServiceNetflixServer {
	
	@RequestMapping(value = "/hystrixMethod", method = RequestMethod.GET)
	String hystrixMethod();
	
	@RequestMapping(value = "/netflixMicroService", method = RequestMethod.GET, consumes = "application/json")
	public NetflixMicroService netflixMicroService();
}
