package edu.ustc.server.config;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

import com.netflix.config.AbstractPollingScheduler;
import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.PolledConfigurationSource;

@Configuration
public class NetflixPropertySourceLocator implements PropertySourceLocator {
	
	private static final Logger logger = LoggerFactory.getLogger(NetflixPropertySourceLocator.class);
	
	@Override
	public PropertySource<?> locate(Environment environment) {
		
		try {
			// configuration from local properties file
			ConcurrentMapConfiguration configFromPropertiesFile = new ConcurrentMapConfiguration(new PropertiesConfiguration("application.yml"));
			
			// configuration from system properties
			ConcurrentMapConfiguration configFromSystemProperties = new ConcurrentMapConfiguration(new SystemConfiguration());
			
			// configuration from a dynamic source
//			PolledConfigurationSource source = new NetflixUrlPolledConfigurationSource();
			PolledConfigurationSource source = new NetflixEurekaPolledConfigurationSource();
			AbstractPollingScheduler scheduler = new FixedDelayPollingScheduler();
			DynamicConfiguration dynamicConfiguration = new DynamicConfiguration(source, scheduler);
			
			// create a hierarchy of configuration that makes
			// 1) dynamic configuration source override system properties
			// 2) system properties override properties file
			ConcurrentCompositeConfiguration finalConfig = new ConcurrentCompositeConfiguration();
			finalConfig.addConfiguration(dynamicConfiguration, "dynamicConfig");
			finalConfig.addConfiguration(configFromSystemProperties, "systemConfig");
			finalConfig.addConfiguration(configFromPropertiesFile, "fileConfig");
			
			// install with ConfigurationManager so that finalConfig
			// becomes the source of dynamic properties
			ConfigurationManager.install(finalConfig);
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return null;
	}
}
