package com.forsfortis.service.dbservice;

import java.util.List;

import com.forsfortis.dao.DiscoveryDAO;
import com.forsfortis.util.NetworkObject;
import com.forsfortis.util.cache.ObjectCache;

public class DiscoveryService {
	DiscoveryDAO discoveryDAO;
	
	
	public DiscoveryDAO getDiscoveryDAO() {
		return discoveryDAO;
	}


	public void setDiscoveryDAO(DiscoveryDAO discoveryDAO) {
		this.discoveryDAO = discoveryDAO;
	}

	//TODO discovery service should create ..
	public void persistNetworkObject(NetworkObject networkObject){
		discoveryDAO.insertDiscoveryObject(networkObject);
	}


	public void loadNetworkObjectCache() {
		final List<NetworkObject> allNetworkObjects = discoveryDAO.getAllNetworkObjects();
		ObjectCache.getInstance().addAll(allNetworkObjects);
	}
	
}
