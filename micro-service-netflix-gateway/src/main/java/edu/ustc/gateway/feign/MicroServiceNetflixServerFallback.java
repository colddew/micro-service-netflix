package edu.ustc.gateway.feign;

import edu.ustc.common.dto.NetflixMicroService;

public class MicroServiceNetflixServerFallback implements MicroServiceNetflixServer {
	
	@Override
	public String hystrixMethod() {
		return "default";
	}
	
	@Override
	public NetflixMicroService netflixMicroService() {
		return null;
	}
}
