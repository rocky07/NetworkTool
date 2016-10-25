package com.forsfortis.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;

import com.forsfortis.dao.DiscoveryDAO;
import com.forsfortis.util.NetworkObject;
import com.forsfortis.util.credentials.Credentials;

public class DiscoveryDaoImpl implements DiscoveryDAO{
	private static final String NETWORK_OBJECTS_COLLECTION="networkobjects";
	private MongoOperations mongoOps;
	
	public DiscoveryDaoImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
	}
	
	@Override
	public void insertDiscoveryObject(NetworkObject networkObject) {
		mongoOps.insert(networkObject,NETWORK_OBJECTS_COLLECTION);
	}

	@Override
	public List<NetworkObject> getAllNetworkObjects(){
		return this.mongoOps.findAll(NetworkObject.class, NETWORK_OBJECTS_COLLECTION);
	}

}
