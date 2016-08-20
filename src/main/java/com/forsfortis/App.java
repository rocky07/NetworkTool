package com.forsfortis;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.forsfortis.agents.AgentManager;
import com.forsfortis.service.ManagementService;
import com.forsfortis.service.dbservice.ServiceFactory;

/**
 * Hello world!
 *
 */
public class App
{
	private static void startAgent(){
		AgentManager agentManager = new AgentManager();
		agentManager.startAgents();
		agentManager.initMessagingSubscribers();
	}
	private static void loadCache(){
		ServiceFactory.getInstance().getDiscoveryService().loadNetworkObjectCache();
	}
    public static void main( String[] args)
    {
    	ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        //TODO init configs : load all init classes and initialise cache with db values
        Server server = new Server();
        ServerConnector http = new ServerConnector(server);
        http.setHost("localhost");
        http.setPort(8081);
        http.setIdleTimeout(30000);
        server.addConnector(http);
        server.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",ManagementService.class.getCanonicalName());
        loadCache();
        startAgent();
        try {
			server.start();
			server.join();
        }catch(Exception e){
        	
        } finally{
        	server.destroy();
        }
    }
}
