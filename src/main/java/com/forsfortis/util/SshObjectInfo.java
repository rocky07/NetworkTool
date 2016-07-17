package com.forsfortis.util;

public class SshObjectInfo extends ObjectInfo {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String sshObjectInfo;
	private double usedMemory;
	private double freememory;

	public SshObjectInfo(NetworkObject networkObject) {
		super(networkObject, DeviceType.SSH);
	}

	public String getSshObjectInfo() {
		return sshObjectInfo;
	}

	public void setSshObjectInfo(String sshObjectInfo) {
		this.sshObjectInfo = sshObjectInfo;
	}

	public double getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(double usedMemory) {
		this.usedMemory = usedMemory;
	}

	public double getFreememory() {
		return freememory;
	}

	public void setFreememory(double freememory) {
		this.freememory = freememory;
	}

}
