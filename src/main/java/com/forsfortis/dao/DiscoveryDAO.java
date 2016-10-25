package com.forsfortis.dao;

import java.util.List;

import com.forsfortis.util.NetworkObject;

public interface DiscoveryDAO {
	public void insertDiscoveryObject(NetworkObject networkObject);
	public List<NetworkObject> getAllNetworkObjects();
}
