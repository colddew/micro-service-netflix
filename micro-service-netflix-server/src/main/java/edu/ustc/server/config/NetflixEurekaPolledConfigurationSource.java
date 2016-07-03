package edu.ustc.server.config;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;
import com.netflix.discovery.DiscoveryManager;

import edu.ustc.server.utils.OkHttpUtils;
import edu.ustc.server.utils.YamlUtils;

/**
 * config spring.cloud.config.discovery in bootstrap.yml
 */
@SuppressWarnings("deprecation")
public class NetflixEurekaPolledConfigurationSource implements PolledConfigurationSource {
	
	@Override
	public PollResult poll(boolean initial, Object checkPoint) throws Exception {
		
		String json = OkHttpUtils.synGetString(getResourceUrl());
		NetflixResource netflixResource = JSON.parseObject(json, NetflixResource.class);
		Map<String, Object> source = netflixResource.getPropertySources().get(0).getSource();
		
		return PollResult.createFull(source);
	}
	
	// resource url is http://colddewdembp.lan:8887/micro-service-netflix-server/prod
	private String getResourceUrl() throws Exception {
		
		String configUrl = getConfigUrl();
		if(StringUtils.isBlank(configUrl)) {
			throw new Exception("config url is null");
		}
		
		Properties properties = YamlUtils.convertYamlToProperties("bootstrap.yml");
		
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
	
	private String getConfigUrl() throws Exception {
		
		List<InstanceInfo> instances = DiscoveryManager.getInstance().getLookupService().getApplications().getRegisteredApplications("micro-service-netflix-config-server").getInstances();
		for(InstanceInfo instanceInfo : instances) {
			try {
				return instanceInfo.getHomePageUrl();
			} catch (Exception e) {
				// do nothing
			}
		}
		
		throw new Exception("config url is null");
	}
}
