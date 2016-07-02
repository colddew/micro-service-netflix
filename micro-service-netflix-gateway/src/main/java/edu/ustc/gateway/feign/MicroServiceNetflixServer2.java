package edu.ustc.gateway.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.ustc.common.dto.NetflixMicroService;

@FeignClient(name = "micro-service-netflix-server2")
@RequestMapping("/api/v1/server2")
public interface MicroServiceNetflixServer2 {
	
	@RequestMapping(value = "/hystrixMethod2", method = RequestMethod.GET)
	String hystrixMethod();
	
	@RequestMapping(value = "/netflixMicroService2", method = RequestMethod.GET, consumes = "application/json")
	public NetflixMicroService netflixMicroService2();
}
