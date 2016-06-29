package edu.ustc.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ustc.gateway.feign.MicroServiceNetflixServer;
import edu.ustc.gateway.feign.MicroServiceNetflixServer2;
import edu.ustc.gateway.service.GatewayService;

@RestController
@RequestMapping("/api/v1/gateway")
public class GatewayController {
	
	@Autowired
	private GatewayService gatewayService;
	
	@Autowired
	private MicroServiceNetflixServer microServiceNetflixServer;
	
	@Autowired
	private MicroServiceNetflixServer2 microServiceNetflixServer2;
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String home() {
		
		gatewayService.invokeRemoteServiceByNativeEurekaClient();
		
        return "hello world gateway !";
    }
	
	@RequestMapping(value = {"/hystrix"}, method = RequestMethod.GET)
	public String hystrix() {
		return gatewayService.invokeRemoteHystrixService();
    }
	
	@RequestMapping(value = {"/ribbon"}, method = RequestMethod.GET)
	public String ribbon() {
		return gatewayService.invokeRemoteServiceByRibbon();
	}
	
	// There is a limitation with the implementation of fallbacks in Feign and how Hystrix fallbacks work.
	// Fallbacks are currently not supported for methods that return com.netflix.hystrix.HystrixCommand and rx.Observable.
	@RequestMapping(value = {"/hystrixFeign"}, method = RequestMethod.GET)
	public String hystrixFeign() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(microServiceNetflixServer.hystrixMethod());
		sb.append(";");
		sb.append(microServiceNetflixServer2.hystrixMethod());
		
		return sb.toString();
	}
	
	@RequestMapping(value = {"/netflixMicroServiceFeign"}, method = RequestMethod.GET)
	public String netflixMicroServiceFeign() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(microServiceNetflixServer.netflixMicroService());
		sb.append(";");
		sb.append(microServiceNetflixServer2.netflixMicroService2());
		
		return sb.toString();
	}
}