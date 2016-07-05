package com.forsfortis.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.forsfortis.dao.CredentialsDAO;
import com.forsfortis.service.dbservice.CredentialService;
import com.forsfortis.util.DeviceType;
import com.forsfortis.util.credentials.Credentials;

public class CredentialServiceTest {
@Test
public void testAddCredentials(){
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	CredentialService credentialService = ctx.getBean("credentialService", CredentialService.class);
    Credentials linuxCredentials=new Credentials();
	linuxCredentials.setUserName("rocky");
	linuxCredentials.setPassword("rocky");
	linuxCredentials.setDeviceType(DeviceType.SSH);
	
	credentialService.addCredentials(linuxCredentials);
    ctx.close();
}

}
