package com.forsfortis.agents;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.forsfortis.dao.CredentialsDAO;
import com.forsfortis.util.DeviceType;
import com.forsfortis.util.NetworkObject;

public abstract class Agent implements AgentInterface {
	Agent(DeviceType deviceType){
		this.deviceType=deviceType;
	}
	protected DeviceType deviceType;
	public void startAgent(){
			TimerTask task = new TimerTask() {
			    @Override
			    public void run() {
			    	getCurrentInfo();
			    }
			};

			Timer timer = new Timer();
			timer.schedule(task, new Date(), 5000);
	} 
	
	private void persistNetworkObject(NetworkObject networkObject){

	}
	
	public void reportDeviceInfo(){

	}
}
