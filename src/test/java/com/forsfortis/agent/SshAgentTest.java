package com.forsfortis.agent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import com.forsfortis.agents.SshAgent;
import com.forsfortis.util.DeviceType;
import com.forsfortis.util.NetworkObject;
import com.forsfortis.util.credentials.CredentialManager;

public class SshAgentTest {
	@Test
	public void testDiscoverAgent() {
		try {
			CredentialManager instance = Mockito.mock(CredentialManager.class);
			NetworkObject obj = new NetworkObject("192.168.1.1", "255.255.255.0", DeviceType.SSH);
			NetworkObject discoverNetworkObjects = new SshAgent().discoverNetworkObjects("192.168.1.1",
					"255.255.255.0");
			Assert.assertEquals(discoverNetworkObjects, obj);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}