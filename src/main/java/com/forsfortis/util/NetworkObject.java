package com.forsfortis.util;

import com.forsfortis.util.credentials.Credentials;

public class NetworkObject {
	private String objectIp;
	private String objectName;
	private String subnet;
	private DeviceType deviceType;
	private DeviceName deviceName;
	private Credentials credentials;
	public NetworkObject(String hostip,String subnet,DeviceType deviceType){
		this.objectIp=hostip;
		this.subnet=subnet;
		this.deviceType=deviceType;
	}
	public DeviceName getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(DeviceName deviceName) {
		this.deviceName = deviceName;
	}
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public String getObjectIp() {
		return objectIp;
	}
	public void setObjectIp(String objectIp) {
		this.objectIp = objectIp;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getSubnet() {
		return subnet;
	}
	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	public String toString(){
		return this.getObjectIp()+":"+this.getDeviceType();
	}
}
