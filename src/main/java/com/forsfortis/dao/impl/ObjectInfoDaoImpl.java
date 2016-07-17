package com.forsfortis.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.forsfortis.dao.ObjectInfoDAO;
import com.forsfortis.util.NetworkObject;
import com.forsfortis.util.ObjectInfo;

public class ObjectInfoDaoImpl implements ObjectInfoDAO {

	private static final String OBJECT_INFO_COLLECTION="objectinfo";
	private MongoOperations mongoOps;
	public ObjectInfoDaoImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
	}
	@Override
	public void insertObjectInfo(ObjectInfo objectInfo) {
		mongoOps.insert(objectInfo,OBJECT_INFO_COLLECTION);
	}

	@Override
	public List<ObjectInfo> listObjectInfo(NetworkObject object) {
		Query query = new Query(Criteria.where("networkObject").is(object));
        return this.mongoOps.find(query,ObjectInfo.class,OBJECT_INFO_COLLECTION);
	}
}
