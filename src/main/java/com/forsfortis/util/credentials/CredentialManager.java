package com.forsfortis.util.credentials;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.forsfortis.service.dbservice.CredentialService;
import com.forsfortis.service.dbservice.ServiceFactory;
import com.forsfortis.util.DeviceType;
//loads the singleton class in memory 
public class CredentialManager {
	static CredentialManager credentialManager;
	List<Credentials> credentialsList = new ArrayList<Credentials>();
	private CredentialManager(){
		CredentialService credentialService = ServiceFactory.getInstance().getCredentialService();
		credentialsList.addAll(credentialService.listAllCredentials());
	}
	public static CredentialManager getInstance(){
		if(credentialManager==null){
			credentialManager=new CredentialManager();
		}
		return credentialManager;
	}
	
	public List<Credentials> loadCredentials(DeviceType deviceType) {
		List<Credentials> filteredlist = new ArrayList<>();
		for (Iterator iterator = credentialsList.iterator(); iterator.hasNext();) {
			Credentials credentials = (Credentials) iterator.next();
			if (credentials.getDeviceType() == deviceType)
				filteredlist.add(credentials);
		}
		return credentialsList;
	}
}
