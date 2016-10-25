package com.forsfortis.util.threshold;

import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import com.forsfortis.util.DeviceType;

public class Thresholdconfigs {
	private int value;
	private boolean active;
	private ThresholdCondition thresholdCondition;
	private DeviceType deviceType;
	
	Thresholdconfigs(int value,ThresholdCondition thresholdCondition,DeviceType deviceType){
		this.value=value;
		this.thresholdCondition=thresholdCondition;
		this.deviceType=deviceType;
		this.active=true;
	}
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public ThresholdCondition getThresholdCondition() {
		return thresholdCondition;
	}
	public void setThresholdCondition(ThresholdCondition thresholdCondition) {
		this.thresholdCondition = thresholdCondition;
	}
	@Override
	public boolean equals(Object obj) {
		Thresholdconfigs config = (Thresholdconfigs) obj;
		if (config.deviceType == this.deviceType && config.value == this.value
				&& config.thresholdCondition == this.thresholdCondition) {
			return true;
		}
		return false;
	}
}
