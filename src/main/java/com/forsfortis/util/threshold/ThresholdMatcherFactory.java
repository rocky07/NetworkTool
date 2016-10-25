package com.forsfortis.util.threshold;

import com.forsfortis.util.DeviceType;
import com.forsfortis.util.threshold.matchers.IcmpThresholdMatcher;
import com.forsfortis.util.threshold.matchers.SshThresholdMatcher;
import com.forsfortis.util.threshold.matchers.WmiThresholdMatcher;

public class ThresholdMatcherFactory {
	public ThresholdMatcher getMatcher(DeviceType deviceType){
		switch (deviceType) {
		case SSH:
			return new SshThresholdMatcher();
		case ICMP:
			return new IcmpThresholdMatcher();
		case WMI:
			return new WmiThresholdMatcher();
		default:
			return null;
		}
	}
}
