package com.forsfortis.agents;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import com.forsfortis.util.DeviceType;
import com.forsfortis.util.NetworkObject;
import com.forsfortis.util.NetworkToolException;
import com.forsfortis.util.NetworkToolExceptionConstants;
import com.forsfortis.util.cache.ObjectCache;

public class IcmpAgent extends Agent {
	
	IcmpAgent() {
		super(DeviceType.ICMP);
	}

	@Override
	public synchronized NetworkObject discoverNetworkObjects(String hostip, String subnet) throws UnknownHostException,IOException,NetworkToolException{
		NetworkObject obj=null;
		int timeOut=2000;
		boolean reachable = InetAddress.getByName(hostip).isReachable(timeOut);
		if(reachable){
			obj=new NetworkObject(hostip,subnet,deviceType);
		}
		return obj;
	}


	@Override
	public void getCurrentInfo() {
		List<NetworkObject> networkObjects = ObjectCache.getInstance().getByDeviceType(deviceType);
		try{
		for (NetworkObject networkObject : networkObjects) {
			int timeOut=2000;
			boolean reachable = InetAddress.getByName(networkObject.getObjectIp()).isReachable(timeOut);
			System.out.println(networkObject.getObjectIp()+" : is reachable="+reachable);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}