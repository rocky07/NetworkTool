package com.forsfortis.util.credentials;

import com.forsfortis.util.DeviceType;

public class Credentials {
	private String id;
	private String userName;
	private String password;
	private DeviceType deviceType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	@Override
	public String toString(){
		return this.userName+":"+this.password+":"+this.deviceType.toString();
	}
	
}
