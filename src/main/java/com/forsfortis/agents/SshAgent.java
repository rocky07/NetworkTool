package com.forsfortis.agents;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.forsfortis.util.DeviceName;
import com.forsfortis.util.DeviceType;
import com.forsfortis.util.NetworkObject;
import com.forsfortis.util.NetworkToolException;
import com.forsfortis.util.cache.ObjectCache;
import com.forsfortis.util.credentials.CredentialManager;
import com.forsfortis.util.credentials.Credentials;
import com.jcabi.ssh.SSH;
import com.jcabi.ssh.SSHByPassword;
import com.jcabi.ssh.Shell;

public class SshAgent extends Agent{
	private static final String DISTRIBUTER_ID = "Distributor ID";

	public SshAgent() {
		super(DeviceType.SSH);
	}
	
	private String parseOSVersionInfo(String stdOut){
		//TODO parse version info for different OS
		//Ubuntu
		String osType="";
		String[] split = stdOut.split("\n");
		for (String string : split) {
			if(string.contains(DISTRIBUTER_ID)){
				osType=string.substring(DISTRIBUTER_ID.length()+1,string.length());
				break;
			}
		}
		return osType.trim();
	}
	
	@Override
	public synchronized NetworkObject discoverNetworkObjects(String hostip, String subnet) throws UnknownHostException, IOException, NetworkToolException {
		try{
		NetworkObject obj=null;
		List<Credentials> credentialsList = CredentialManager.getInstance().loadCredentials(this.deviceType);
		for (Credentials objectCredentials : credentialsList) {
			SSHByPassword sshByPassword = new SSHByPassword(hostip, 22, objectCredentials.getUserName(), objectCredentials.getPassword());
			String stdout = new Shell.Plain(sshByPassword).exec("lsb_release -a");
			System.out.println(stdout);
			if(parseOSVersionInfo(stdout).equals(DeviceName.Ubuntu.toString())){
				obj=new NetworkObject(hostip,subnet,DeviceType.SSH);
				obj.setDeviceName(DeviceName.Ubuntu);
				obj.setCredentials(objectCredentials);
				return obj;
			}
		}
		}catch(Exception e){
		//	e.printStackTrace();
		}
		return null;
	}
	
	private void getandPersistCurrentInfo(NetworkObject networkObject){
		try{
			NetworkObject obj=null;
			List<Credentials> credentialsList = CredentialManager.getInstance().loadCredentials(this.deviceType);
				SSHByPassword sshByPassword = new SSHByPassword(networkObject.getObjectIp(), 22, networkObject.getCredentials().getUserName(), networkObject.getCredentials().getPassword());
				String stdout = new Shell.Plain(sshByPassword).exec("lscpu");
				System.out.println(stdout);
			}catch(Exception e){
			//	e.printStackTrace();
			}
	}
	

	public void getCurrentInfo() {
		List<NetworkObject> sshObjects = ObjectCache.getInstance().getByDeviceType(deviceType);
		for (NetworkObject networkObject : sshObjects) {
			getandPersistCurrentInfo(networkObject);
		}
	}
}
