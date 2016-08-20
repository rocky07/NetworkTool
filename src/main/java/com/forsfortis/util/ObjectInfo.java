package com.forsfortis.util;

import java.io.Serializable;
import java.util.Date;

public class ObjectInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NetworkObject networkObject;
	private boolean alive;
	private DeviceType deviceType;
	
	ObjectInfo(NetworkObject networkObject,DeviceType deviceType){
		this.networkObject=networkObject;
		this.deviceType=deviceType;
		this.timestamp=new Date().getTime();
	}
	public NetworkObject getNetworkObject() {
		return networkObject;
	}
	public void setNetworkObject(NetworkObject networkObject) {
		this.networkObject = networkObject;
	}
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	private long timestamp;
}
