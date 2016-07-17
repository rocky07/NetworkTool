package com.forsfortis.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.forsfortis.agents.AgentManager;
import com.forsfortis.discovery.DiscoveryEngine;

@Path("managementservice")
public class ManagementService {
	@GET
    @Path("startdiscovery")
	 @Produces(MediaType.APPLICATION_JSON)
	public Response startDiscovery(){
	new DiscoveryEngine().startDiscovery();	
	return Response.status(200).entity("Discovery started").build();
	}
	@GET
    @Path("startagent")
	 @Produces(MediaType.APPLICATION_JSON)
	public Response startAgent(){
		//TODO load saved network objects to Object cache
		AgentManager agentManager = new AgentManager();
		agentManager.startAgents();
		agentManager.initMessagingSubscribers();
	return Response.status(200).entity("Agent started").build();
	}
	
	
}
