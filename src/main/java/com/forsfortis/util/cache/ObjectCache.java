package com.forsfortis.util.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.forsfortis.service.dbservice.ServiceFactory;
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
		if(!cache.keySet().contains(getUid(object))){
			cache.put(getUid(object), object);
			ServiceFactory.getInstance().getDiscoveryService().persistNetworkObject(object);	
		}
	}
	//TODO initialize the cache with the db data
	public void addAll(List<NetworkObject> networkObjects){
		for (Iterator<NetworkObject> iterator = networkObjects.iterator(); iterator.hasNext();) {
			NetworkObject networkObject = iterator.next();
			if (!cache.keySet().contains(getUid(networkObject))) {
				cache.put(getUid(networkObject), networkObject);
			}
		}
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
