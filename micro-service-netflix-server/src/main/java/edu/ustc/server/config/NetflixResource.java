package edu.ustc.server.config;

import java.util.List;
import java.util.Map;

public class NetflixResource {
	
	private String name;
	private List<String> profiles;
	private String label;
	private String version;
	private List<NetflixPropertySource> propertySources;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getProfiles() {
		return profiles;
	}
	
	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public List<NetflixPropertySource> getPropertySources() {
		return propertySources;
	}
	
	public void setPropertySources(List<NetflixPropertySource> propertySources) {
		this.propertySources = propertySources;
	}
	
	class NetflixPropertySource {
		
		private String name;
		private Map<String, Object> source;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public Map<String, Object> getSource() {
			return source;
		}
		
		public void setSource(Map<String, Object> source) {
			this.source = source;
		}
	}
}
