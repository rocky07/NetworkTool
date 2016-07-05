package com.forsfortis.agents;

import com.forsfortis.util.DeviceType;
import com.forsfortis.util.NetworkObject;
import com.forsfortis.util.cache.ObjectCache;

public class AgentManager{
	//TODO discover network objects
	public NetworkObject discoverNetworkDevice(String hostip,String subnet){
		NetworkObject networkObject=null;
		try{
			//credential based discovery
			networkObject = new SshAgent().discoverNetworkObjects(hostip, subnet);
			if(networkObject==null){ //check default icmp object
				networkObject=new IcmpAgent().discoverNetworkObjects(hostip, subnet);
			}
		}catch(Throwable e){
			e.printStackTrace();
		}
		//populates the object cache
		ObjectCache.getInstance().add(networkObject);
		System.out.println("ssh obejct: "+networkObject);
		return networkObject;
	}
	//TODO fetch current info
	//TODO persist the network device
	//TODO start reporting agents
	public void startAgents(){
		//
		new SshAgent().startAgent();
		new IcmpAgent().startAgent();
	}
}