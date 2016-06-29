package edu.ustc.gateway.feign;

import edu.ustc.common.dto.NetflixMicroService;

public class MicroServiceNetflixServer2Fallback implements MicroServiceNetflixServer2 {
	
	@Override
	public String hystrixMethod() {
		return "defalut2";
	}
	
	@Override
	public NetflixMicroService netflixMicroService2() {
		return null;
	}
}