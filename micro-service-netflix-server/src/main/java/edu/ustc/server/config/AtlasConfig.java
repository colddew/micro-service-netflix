package edu.ustc.server.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.metrics.atlas.AtlasTagProvider;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtlasConfig {
	
	public AtlasTagProvider atlasCommonTags(@Value("${spring.application.name}") String appName) {
		return () -> Collections.singletonMap("app", appName);
	}
}
