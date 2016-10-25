package com.forsfortis.util.threshold;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.forsfortis.util.DeviceType;

public class ThresholdManager {
	private static ThresholdManager thresholdManager;
	private static Set<Thresholdconfigs> allThresholdConfigs=new HashSet<Thresholdconfigs>();
 	private ThresholdManager(){
		//init values
		init();
	}
 	
	public static ThresholdManager getInstance(){
		if(thresholdManager==null){
			thresholdManager=new ThresholdManager();
		}
		return thresholdManager;
	}
	
	public List<Thresholdconfigs> getThresholdConfigs(DeviceType deviceType){//filters threshold configs by devicetype
		List<Thresholdconfigs> filteredList=new ArrayList<>();
		for (Iterator<Thresholdconfigs> iterator = allThresholdConfigs.iterator(); iterator.hasNext();) {
			Thresholdconfigs thresholdconfigs = iterator.next();
			if(thresholdconfigs.getDeviceType()==deviceType){
				filteredList.add(thresholdconfigs);
			}
		}
		return filteredList;
	}
	
	private void init(){
		allThresholdConfigs.add(new Thresholdconfigs(82, ThresholdCondition.GREATERTHAN, DeviceType.SSH));
		allThresholdConfigs.add(new Thresholdconfigs(82, ThresholdCondition.LESSTHAN, DeviceType.SSH));
	}
	
}
