package com.forsfortis.service.dbservice;

import java.util.List;

import com.forsfortis.dao.ObjectInfoDAO;
import com.forsfortis.util.NetworkObject;
import com.forsfortis.util.ObjectInfo;

public class ObjectInfoService {
	ObjectInfoDAO objectInfoDAO;

	public ObjectInfoDAO getObjectInfoDAO() {
		return objectInfoDAO;
	}

	public void setObjectInfoDAO(ObjectInfoDAO objectInfoDAO) {
		this.objectInfoDAO = objectInfoDAO;
	}

	public void insertObjectInfo(ObjectInfo objectInfo) {
		objectInfoDAO.insertObjectInfo(objectInfo);
	}

	public List<ObjectInfo> listObjectInfo(NetworkObject object) {
		return objectInfoDAO.listObjectInfo(object);
	}
}
