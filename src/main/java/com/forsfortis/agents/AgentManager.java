package com.forsfortis.agents;

import javax.jms.JMSException;

import com.forsfortis.messages.MsgPublisher;
import com.forsfortis.messages.MsgSubscriber;
import com.forsfortis.util.ApplicationConstants;
import com.forsfortis.util.DeviceType;
import com.forsfortis.util.NetworkObject;
import com.forsfortis.util.cache.ObjectCache;

public class AgentManager{
	private static MsgPublisher publisher=null;
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
		new SshAgent().startAgent();
		new IcmpAgent().startAgent();
	}
	
	public static MsgPublisher getPublisher(){
		if(publisher==null){
		publisher=new MsgPublisher();
		try {
			publisher.create(ApplicationConstants.APPLICATION_PUBLISHER_CLIENT_ID, ApplicationConstants.DB_PERSISTANCE_TOPIC);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		}
		return publisher;
	}
	
	
	public void initMessagingSubscribers(){
		try{
			MsgSubscriber subscriber=new MsgSubscriber();
			subscriber.create(ApplicationConstants.APPLICATION_SUBSCRIBER_CLIENT_ID, ApplicationConstants.DB_PERSISTANCE_TOPIC);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}