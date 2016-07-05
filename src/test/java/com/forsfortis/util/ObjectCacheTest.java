package com.forsfortis.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.forsfortis.util.cache.ObjectCache;

public class ObjectCacheTest{
	NetworkObject nwkObject = null;

	@Before
	public void setUp() throws Exception {
		nwkObject = new NetworkObject("127.0.0.1", "192.168.1", DeviceType.SSH);
	};

	@Test
	public void getObjectCache() {
		
		ObjectCache.getInstance().add(nwkObject);
		System.out.println(ObjectCache.getInstance().getByDeviceType(DeviceType.SSH));
		Assert.assertEquals(nwkObject, ObjectCache.getInstance().getByDeviceType(DeviceType.SSH).get(0));
	}

	@After
	public void tearDown() {
		// TODO Auto-generated method stub
		nwkObject = null;
	}
}
