package edu.ustc.server.config;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;

import edu.ustc.server.utils.OkHttpUtils;
import edu.ustc.server.utils.YamlUtils;

/**
 * config spring.cloud.config.url in bootstrap.yml
 */
public class NetflixUrlPolledConfigurationSource implements PolledConfigurationSource {
	
	@Override
	public PollResult poll(boolean initial, Object checkPoint) throws Exception {
		
		String json = OkHttpUtils.synGetString(getResourceUrl());
		NetflixResource netflixResource = JSON.parseObject(json, NetflixResource.class);
		Map<String, Object> source = netflixResource.getPropertySources().get(0).getSource();
		
		return PollResult.createFull(source);
	}
	
	// resource url is http://config-repo.com/micro-service-netflix-server/prod
	private String getResourceUrl() throws Exception {
		
		Properties properties = YamlUtils.convertYamlToProperties("bootstrap.yml");
		
		String configUrl = properties.getProperty("spring.cloud.config.uri");
		if(StringUtils.isNotBlank(configUrl)) {
			configUrl = StringUtils.substringBetween(configUrl, "SPRING_CONFIG_URI:", "}");
		}
		
		if(StringUtils.isBlank(configUrl)) {
			throw new Exception("spring.cloud.config.uri is null");
		}
		
		String applicationName = properties.getProperty("spring.application.name");
		if(StringUtils.isBlank(applicationName)) {
			throw new Exception("spring.application.name is null");
		}
		
		String profiles = properties.getProperty("spring.profiles.active");;
		if(StringUtils.isBlank(profiles)) {
			throw new Exception("spring.profiles.active is null");
		}
		
		String activeProfile = StringUtils.substringAfterLast(profiles, ",");
		if(StringUtils.isBlank(activeProfile)) {
			throw new Exception("spring.profiles.active is error");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(configUrl).append("/").append(applicationName).append("/").append(activeProfile);
		
		return sb.toString();
	}
}
