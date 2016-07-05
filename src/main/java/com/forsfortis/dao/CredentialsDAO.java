package com.forsfortis.dao;

import java.util.List;

import com.forsfortis.util.DeviceType;
import com.forsfortis.util.credentials.Credentials;

public interface CredentialsDAO {
	public void insert(Credentials credentials);
	public List<Credentials> listAllCredentials();
}
