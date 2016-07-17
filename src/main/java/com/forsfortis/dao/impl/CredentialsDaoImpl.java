package com.forsfortis.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;

import com.forsfortis.dao.CredentialsDAO;
import com.forsfortis.util.credentials.Credentials;

public class CredentialsDaoImpl implements CredentialsDAO {
	
	private static final String CREDENTIALS_COLLECTION="credentials";
	private MongoOperations mongoOps;
	public CredentialsDaoImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
	}
	
	@Override
	public void insert(Credentials credentials) {
		mongoOps.insert(credentials,CREDENTIALS_COLLECTION);
	}

	@Override
	public List<Credentials> listAllCredentials() {
		//Query query = new Query(Criteria.where("deviceType").is(deviceType));
        return this.mongoOps.findAll(Credentials.class, CREDENTIALS_COLLECTION);
	}
}
