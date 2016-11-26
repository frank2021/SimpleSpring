package com.tigerz.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryOperators;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDao implements MongoService {
	MongoClient mongoClient = null;

	public MongoDao(String dbHost, int port) {
		initMongo(dbHost, port);
	}

	public MongoClient initMongo(String dbHost, int port) {
		if (mongoClient == null) {
			mongoClient = new MongoClient(dbHost, port);
		}
		return mongoClient;
	}

	public MongoDatabase getDataBase(String DataBaseName) {
		if (mongoClient == null) {
			mongoClient = initMongo();
		}
		MongoDatabase database = mongoClient.getDatabase(DataBaseName);
		return database;
	}

	public MongoCollection<Document> getCollection(MongoDatabase database, String collectionName) {
		MongoCollection<Document> collection = database.getCollection(collectionName);
		if (collection == null) {
			createCollection(database, collectionName, false);
			collection = database.getCollection(collectionName);
		}
		return collection;
	}

	public void createCollection(MongoDatabase database, String collectionName, boolean isDrop) {
		MongoCollection<Document> collection = database.getCollection(collectionName);
		if (collection == null) {
			database.createCollection(collectionName);
		} else if (isDrop) {
			collection.drop();
			database.createCollection(collectionName);
		}
	}

	public void dropCollection(MongoCollection<Document> collcetion) {
		if (collcetion != null) {
			collcetion.drop();
		}
	}

	public long getCollectionCount(MongoCollection<Document> collcetion) {
		if (collcetion != null) {
			return collcetion.count();
		}
		return 0;
	}

	public long getCount(MongoCollection<Document> collcetion, BasicDBObject obj) {
		if (collcetion != null) {
			return collcetion.count(obj);
		}
		return 0;
	}

	/**
	 * Find doc for polygon in GEO
	 * 
	 * @param collcetion
	 * @param polygon
	 *            Take care the type ; List<Double[]>
	 * @return
	 */
	public List<Document> findByPolygon(MongoCollection<Document> collcetion, List<Double[]> polygon) {
		List<Document> docList = new ArrayList<Document>();

		BasicDBObject dbObject = new BasicDBObject();
		BasicDBObject searchObj = new BasicDBObject(QueryOperators.WITHIN,
				new BasicDBObject(QueryOperators.POLYGON, polygon));
		dbObject.put("base_point", searchObj);
		// System.out.println("searchStr:"+dbObject.toJson().toString());
		FindIterable<Document> mFindIterable = collcetion.find(dbObject);
		MongoCursor<Document> cursor = mFindIterable.iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			docList.add(doc);
		}

		return docList;
	}

	/**
	 * Find doc For Sphere in GEO
	 * 
	 * @param collcetion
	 * @param colName
	 *            the name of column which is base point array
	 * @param centerPoint
	 *            the center point
	 * @param radius
	 *            the unit is kilometer
	 * @return
	 */
	public List<Document> findBySphere(MongoCollection<Document> collcetion, String colName,
			ArrayList<Double> cenerPoint, double radius) {
		List<Document> docList = new ArrayList<Document>();

		FindIterable<Document> mFindIterable = collcetion
				.find(Filters.geoWithinCenterSphere(colName, cenerPoint.get(0), cenerPoint.get(1), radius / 6371));

		MongoCursor<Document> cursor = mFindIterable.iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			docList.add(doc);
		}

		return docList;
	}

	public List<Document> findBySphere2(String collectionName, String fieldName, BasicDBObject query,
			ArrayList<Double> cenerPoint, double radiusByMeter) {
		List<Document> docList = new ArrayList<Document>();
		if (query == null) {
			query = new BasicDBObject();
		}

		LinkedList<Object> circle = new LinkedList<>();
		// Set the center coordinate
		circle.addLast(new double[] { cenerPoint.get(0), cenerPoint.get(1)});
		// Set the radius. unit:meter
		circle.addLast(radiusByMeter / 6378137.0);
		
		query.put(fieldName, new BasicDBObject("$geoWithin",new BasicDBObject("$centerSphere", circle)));

		
//		MongoCursor<Document> cursor = mFindIterable.iterator();
//		while (cursor.hasNext()) {
//			Document doc = cursor.next();
//			docList.add(doc);
//		}

		return docList;
	}

	public List<Document> findByBox() {
		return null;

	}

	public List<Document> find(MongoCollection<Document> collcetion, BasicDBObject query) {
		List<Document> docList = new ArrayList<Document>();
		if (collcetion != null) {
			FindIterable<Document> mFindIterable = null;
			if (query != null) {
				mFindIterable = collcetion.find(query);
			} else {
				mFindIterable = collcetion.find();
			}
			MongoCursor<Document> cursor = mFindIterable.iterator();
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				docList.add(doc);
			}
		}
		return docList;
	}

	public List<Document> find(MongoCollection<Document> collcetion, BasicDBObject query, BasicDBObject sort) {
		List<Document> docList = new ArrayList<Document>();
		if (collcetion != null) {
			FindIterable<Document> mFindIterable = null;
			if (query != null) {
				mFindIterable = collcetion.find(query);
			} else {
				mFindIterable = collcetion.find();
			}
			if (sort != null) {
				mFindIterable.sort(sort);
			}
			MongoCursor<Document> cursor = mFindIterable.iterator();
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				docList.add(doc);
			}
		}
		return docList;
	}

	public List<Document> find(MongoCollection<Document> collcetion, BasicDBObject query, BasicDBObject sort, int start,
			int limit) {
		List<Document> docList = new ArrayList<Document>();
		if (collcetion != null) {
			FindIterable<Document> mFindIterable = null;
			if (query != null) {
				mFindIterable = collcetion.find(query);
			} else {
				mFindIterable = collcetion.find();
			}
			if (sort != null) {
				mFindIterable.sort(sort);
			}
			if (start == 0) {
				mFindIterable.limit(limit);
			} else {
				mFindIterable.skip(start).limit(limit);
			}
			MongoCursor<Document> cursor = mFindIterable.iterator();
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				docList.add(doc);
			}
		}
		return docList;
	}

	public List<String> distinct(MongoCollection<Document> collcetion, String suburb) {
		List<String> docList = new ArrayList<String>();
		if (collcetion != null) {
			Bson bs = new BasicDBObject("suburb", new BasicDBObject("$ne", null));
			MongoCursor<String> suburbList = collcetion.distinct(suburb, bs, String.class).iterator();
			while (suburbList.hasNext()) {
				docList.add(suburbList.next());
			}
		}
		return docList;
	}

	public void update(MongoCollection<Document> collcetion, BasicDBObject setFields, BasicDBObject whereFields) {
		if (collcetion != null) {
			collcetion.updateOne(setFields, whereFields);
		}
	}

	public void updateBatch(MongoCollection<Document> collcetion, BasicDBObject setFields, BasicDBObject whereFields) {
		if (collcetion != null) {
			collcetion.updateMany(setFields, whereFields);
		}
	}

	public List<Document> findAll(MongoCollection<Document> collcetion) {
		FindIterable<Document> mFindIterable = collcetion.find();
		MongoCursor<Document> cursor = mFindIterable.iterator();
		List<Document> docList = new ArrayList<Document>();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			docList.add(doc);
		}
		return docList;
	}

	/**
	 * Find all document by callback method
	 * 
	 * @param collcetion
	 * @param handler
	 *            the implementation object of MongoDocHandler
	 */
	public void findAll(MongoCollection<Document> collcetion, MongoDocHandler handler) {
		FindIterable<Document> mFindIterable = collcetion.find();
		MongoCursor<Document> cursor = mFindIterable.iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			handler.useOneDoc(doc);
		}
	}

	public void insert(MongoCollection<Document> collcetion, Document obj) {
		if (collcetion != null) {
			collcetion.insertOne(obj);
		}
	}

	public void insertBatch(MongoCollection<Document> collcetion, List<Document> list) {
		if (collcetion != null) {
			collcetion.insertMany(list);
		}

	}

	public void delete(MongoCollection<Document> collcetion, BasicDBObject query) {
		if (collcetion != null) {
			collcetion.deleteOne(query);
		}
	}

	public void deleteBatch(MongoCollection<Document> collcetion, BasicDBObject query) {
		if (collcetion != null) {
			collcetion.deleteMany(query);
		}
	}

	public void release() {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}

	@Override
	public MongoClient initMongo() {
		// TODO Auto-generated method stub
		return null;
	}

}
