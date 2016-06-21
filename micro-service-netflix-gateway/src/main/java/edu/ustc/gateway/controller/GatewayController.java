package edu.ustc.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ustc.gateway.service.GatewayService;

@RestController
@RequestMapping("/api/v1/gateway")
public class GatewayController {
	
	@Autowired
	private GatewayService gatewayService;
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String home() {
		
		gatewayService.invokeRemoteServiceByNativeEurekaClient();
		
        return "hello world gateway !";
    }
}