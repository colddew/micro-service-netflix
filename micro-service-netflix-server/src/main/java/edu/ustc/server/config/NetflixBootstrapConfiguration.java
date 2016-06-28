package edu.ustc.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ @PropertySource("classpath:application.yml") })
public class NetflixBootstrapConfiguration {
	
	@Bean
	public NetflixPropertySourceLocator netflixPropertySourceLocator() {
		return new NetflixPropertySourceLocator();
	}
}
