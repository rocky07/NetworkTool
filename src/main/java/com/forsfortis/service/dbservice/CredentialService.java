package com.forsfortis.service.dbservice;

import java.util.List;

import com.forsfortis.dao.CredentialsDAO;
import com.forsfortis.util.DeviceType;
import com.forsfortis.util.credentials.Credentials;

public class CredentialService {                                                                                                           
	CredentialsDAO credentailsDAO;
	public CredentialService(){
	}
	
	public CredentialsDAO getCredentailsDAO() {
		return credentailsDAO;
	}

	public void setCredentailsDAO(CredentialsDAO credentailsDAO) {
		this.credentailsDAO = credentailsDAO;
	}

	//TODO create service method to create new configuration
	public void addCredentials(Credentials credentials){
		credentailsDAO.insert(credentials);
	}
	//TODO service method to update new config
	//TODO service method to delete config
	public List<Credentials> listAllCredentials(){
		return credentailsDAO.listAllCredentials();
	}
	
}
