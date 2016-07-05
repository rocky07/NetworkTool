package com.forsfortis.discovery;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.forsfortis.agents.AgentManager;
import com.forsfortis.util.NetworkObject;

public final class DiscoveryEngine implements Runnable {
	private static final int timeout=1000;
	private static final String subnet="192.168.1";
	private static boolean isDiscoveryStarter=false;
	private String host;
	
	public DiscoveryEngine(){
		
	}
	DiscoveryEngine(String host){
		this.host=host;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	public void startDiscovery(){
		//runs the discovery for the set gateway
		   for (int i=1;i<254;i++){
		       String host=subnet + "." + i;
		      Thread t=new Thread(new DiscoveryEngine(host));
		      t.start();
		   }
	}

	@Override
	public void run() {
		try {
			if (InetAddress.getByName(host).isReachable(timeout)) {
				System.out.println(host + " is reachable");
				new AgentManager().discoverNetworkDevice(host, subnet);
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
