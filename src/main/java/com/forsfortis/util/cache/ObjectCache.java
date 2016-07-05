package com.forsfortis.util.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.forsfortis.util.DeviceType;
import com.forsfortis.util.NetworkObject;

public class ObjectCache {
	private static ObjectCache objCache;
	private ConcurrentHashMap<String, NetworkObject> cache=new ConcurrentHashMap<String,NetworkObject>();
	private ObjectCache(){
		
	}
	public static ObjectCache getInstance(){
		if(objCache==null){
			objCache=new ObjectCache();
		}
		return objCache;
	}
	private String getUid(NetworkObject object){
		return object.getObjectIp()+":"+object.getDeviceType();
	}
	public void add(NetworkObject object){
		cache.put(getUid(object), object);
	}
	//TODO revice the code to check for leaks
	public List<NetworkObject> getByDeviceType(DeviceType deviceType){
		Collection<NetworkObject> values = cache.values();
		List<NetworkObject> filteredList=new ArrayList<NetworkObject>();
		for (NetworkObject networkObject : values) {
			if(networkObject.getDeviceType()==deviceType){
				filteredList.add(networkObject);
			}		
		}
		return filteredList;
	} 
	
	

}
