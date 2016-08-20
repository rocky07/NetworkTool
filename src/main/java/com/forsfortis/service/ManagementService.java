package com.forsfortis.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.forsfortis.agents.AgentManager;
import com.forsfortis.discovery.DiscoveryEngine;
import com.forsfortis.util.NetworkToolException;

@Path("managementservice")
public class ManagementService {
	@POST
    @Path("startdiscovery")
	@Consumes("application/x-www-form-urlencoded")
	public Response startDiscovery(MultivaluedMap<String, String> formParams){
		System.out.println("-------------------Discovery started with ips : "+formParams.get("startIp")+":"+formParams.get("endIp"));
		try{
			new DiscoveryEngine().startDiscovery(String.valueOf(formParams.getFirst("startIp")),String.valueOf(formParams.getFirst("endIp")));	
		}catch(NetworkToolException e){
			return Response.status(200).entity("Discovery can't be started until the previouse one finshes").build();
		}
		return Response.status(200).entity("Discovery started").build();
	}
	
	@GET
    @Path("resetdiscovery")
	 @Produces(MediaType.APPLICATION_JSON)
	public Response resetDiscovery(){
			DiscoveryEngine.resetDiscovery();	
		return Response.status(200).entity("Discovery Reset").build();
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
