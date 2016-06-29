package edu.ustc.common.dto;

import java.util.Date;

public class NetflixMicroService {
	
	private Integer id;
	private String name;
	private String status;
	private Date uptime;
	
	public NetflixMicroService() {
		
	}
	
	public NetflixMicroService(Integer id, String name, String status, Date uptime) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.uptime = uptime;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getUptime() {
		return uptime;
	}
	
	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}
}
