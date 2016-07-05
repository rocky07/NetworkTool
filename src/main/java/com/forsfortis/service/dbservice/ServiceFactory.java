package com.forsfortis.service.dbservice;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceFactory {
	private static ServiceFactory instance;
	ClassPathXmlApplicationContext ctx;
	private ServiceFactory(){
		ctx = new ClassPathXmlApplicationContext("spring.xml");
	}
	public static ServiceFactory getInstance(){
		if(instance==null){
			instance=new ServiceFactory();
		} 
		return instance;
	}
	
	public CredentialService getCredentialService(){
		return ctx.getBean("credentialService", CredentialService.class);
	}
}
