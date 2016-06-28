package edu.ustc.server.utils;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlProcessor.ResolutionMethod;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

public class YamlUtils {
	
	public static Properties convertYamlToProperties(String yamlFile) {
		
		YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
		yamlPropertiesFactoryBean.setResources(new ClassPathResource(yamlFile));
		yamlPropertiesFactoryBean.setResolutionMethod(ResolutionMethod.FIRST_FOUND);
		
		return yamlPropertiesFactoryBean.getObject();
	}
	
	public static Map<String, Object> convertYamlToMap(String yamlFile) {
		
		YamlMapFactoryBean yamlMapFactoryBean = new YamlMapFactoryBean();
		yamlMapFactoryBean.setResources(new ClassPathResource(yamlFile));
		yamlMapFactoryBean.setResolutionMethod(ResolutionMethod.FIRST_FOUND);
		
		return yamlMapFactoryBean.getObject();
	}
}
