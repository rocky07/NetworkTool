package com.forsfortis.dao;

import java.util.List;

import com.forsfortis.util.NetworkObject;
import com.forsfortis.util.ObjectInfo;

public interface ObjectInfoDAO {
	public void insertObjectInfo(ObjectInfo objectInfo);
	public List<ObjectInfo> listObjectInfo(NetworkObject object);
}
