package com.forsfortis.util;

public class IcmpObjectInfo extends ObjectInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String info;
	public IcmpObjectInfo(NetworkObject networkObject){
		super(networkObject,DeviceType.ICMP);
	}
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	} 
}
