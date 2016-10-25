package com.forsfortis.util.threshold.matchers;

import java.util.Iterator;
import java.util.List;

import com.forsfortis.util.ObjectInfo;
import com.forsfortis.util.SshObjectInfo;
import com.forsfortis.util.threshold.ThresholdManager;
import com.forsfortis.util.threshold.ThresholdMatcher;
import com.forsfortis.util.threshold.Thresholdconfigs;

public class SshThresholdMatcher extends ThresholdMatcher {

	@Override
	public void match(ObjectInfo objectInfo) {
		// TODO Auto-generated method stub
		SshObjectInfo sshObjectInfo=(SshObjectInfo)objectInfo;
		List<Thresholdconfigs> thresholdConfigs = ThresholdManager.getInstance().getThresholdConfigs(objectInfo.getDeviceType());
		for (Iterator<Thresholdconfigs> iterator = thresholdConfigs.iterator(); iterator.hasNext();) {
			Thresholdconfigs thresholdconfig = (Thresholdconfigs) iterator.next();
			if(sshObjectInfo.getUsedMemory()>thresholdconfig.getValue()){
				System.out.println("new incident generated -------------------->>");
			}else{
				System.out.println("no new incident generated ------------->>");
			}
		}
	}
}
