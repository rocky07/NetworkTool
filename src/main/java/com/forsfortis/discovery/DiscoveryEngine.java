package com.forsfortis.discovery;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.forsfortis.agents.AgentManager;
import com.forsfortis.util.NetworkToolException;

public final class DiscoveryEngine implements Runnable {
	private static final int timeout=1000;
	private static final String subnet="192.168.0";
	private static int objectcount=0;
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
	
	private static boolean isObjectDiscoveryInProgress(){
		return objectcount>0?true:false;
	}
	
	public static void resetDiscovery(){
		objectcount=0;
	}
	
	public void startDiscovery(String startIp,String endIp) throws NetworkToolException{
		if(isObjectDiscoveryInProgress()){
			//new discovery will not start unless all old process have terminated
			throw new NetworkToolException(NetworkToolException.DISCOVERY_IN_PROGRESS);
		}
		objectcount=0;
		//runs the discovery for the set gateway
		//TODO main code need to uncomment after testing
		final String[] splitStartIp = startIp.split("\\.");
		final String[] splitEndIp = endIp.split("\\.");
		if(splitStartIp.length>3){//validates ip
			final int hostPartStartIp = Integer.valueOf(splitStartIp[3]);
			final int hostPartEndIp = Integer.valueOf(splitEndIp[3]);
		
		   for (int i=hostPartStartIp;i<hostPartEndIp;i++){
			   objectcount+=1;
		       String host=subnet + "." + i;
		      Thread t=new Thread(new DiscoveryEngine(host));
		      t.start();
		   }
		}
		//test code
		   /*Thread t=new Thread(new DiscoveryEngine("127.0.0.1"));
		   objectcount+=1;
		      t.start();*/
		   
	}

	@Override
	public void run() {
		try {
			if (InetAddress.getByName(host).isReachable(timeout)) {
				System.out.println(host + " is reachable");
				new AgentManager().discoverNetworkDevice(host, subnet);
				objectcount--;
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			objectcount--;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			objectcount--;
			e.printStackTrace();
		}
	}
	
}
