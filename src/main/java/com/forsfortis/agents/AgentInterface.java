package com.forsfortis.agents;

import java.io.IOException;
import java.net.UnknownHostException;

import com.forsfortis.util.NetworkToolException;
import com.forsfortis.util.DeviceType;
import com.forsfortis.util.NetworkObject;

public interface AgentInterface {
	public NetworkObject discoverNetworkObjects(String hostip, String subnet) throws UnknownHostException,IOException,NetworkToolException;
	public void getCurrentInfo();
}
