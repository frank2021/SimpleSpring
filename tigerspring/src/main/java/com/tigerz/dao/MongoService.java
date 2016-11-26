package com.tigerz.dao;

import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public interface MongoService {
	/**
	 * 初始化mongo
	 * @return
	 */
	public MongoClient initMongo(); 
	/**
	 * 初始化mongo
	 * @return
	 */
	public MongoClient initMongo(String dbHost,int port);
	/**
	 * 获取数据库
	 * @param DataBaseName 数据库名称
	 * @return
	 */
	public MongoDatabase getDataBase(String DataBaseName);
	/**
	 * 获取数据库中的数据表
	 * @param collectionName 表名
	 * @return
	 */
	public MongoCollection<Document> getCollection(MongoDatabase database,String collectionName);
	/**
	 * 创建表
	 * @param database 库对象，collcetionName 表名
	 */
	public void createCollection(MongoDatabase database,String collectionName,boolean isDrop); 
	/**
	 * 删除表
	 * @param collcetion 表对象
	 */
	public void dropCollection(MongoCollection<Document> collcetion);
	/**
	 * 获取集合中的数据数量
	 * @param collcetion
	 * @return
	 */
	public long getCollectionCount(MongoCollection<Document> collcetion);
	/**
	 * 查找符合条件的数据数量
	 * @param collcetion
	 * @param obj
	 * @return
	 */
	public long getCount(MongoCollection<Document> collcetion,BasicDBObject obj);
	/**
	 * 查找符合条件的数据
	 */
	public List<Document> find(MongoCollection<Document> collcetion,BasicDBObject query);
	/**
	 * 查找符合条件的数据并排序
	 * @param query
	 * @param sort
	 * @return
	 */
	public List<Document> find(MongoCollection<Document> collcetion,BasicDBObject query, BasicDBObject sort);
	/**
	 * 查找符合条件的数据并排序，规定数据个数
	 * @param query
	 * @param sort
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Document> find(MongoCollection<Document> collcetion,BasicDBObject query, BasicDBObject sort, int start,int limit);
	/**
	 * 更新数据
	 * @param collcetion
	 * @param setFields
	 * @param whereFields
	 */
	public void update(MongoCollection<Document> collcetion,BasicDBObject setFields, BasicDBObject whereFields);
	/**
	 * 更新多条数据
	 * @param collcetion
	 * @param setFields
	 * @param whereFields
	 */
	public void updateBatch(MongoCollection<Document> collcetion,BasicDBObject setFields, BasicDBObject whereFields);
	/**
	 * 查询集合中所有数据
	 * @param collcetion
	 * @return
	 */
	public List<Document> findAll(MongoCollection<Document> collcetion);
	/**
	 * 插入数据
	 * @param collcetion
	 * @param obj
	 */
	public void insert(MongoCollection<Document> collcetion,Document obj);
	/**
	 * 插入多条数据
	 * @param collcetion
	 * @param list
	 */
	public void insertBatch(MongoCollection<Document> collcetion,List<Document> list);
	/**
	 * 删除数据
	 * @param obj
	 */
	public void delete(MongoCollection<Document> collcetion,BasicDBObject obj);
	/**
	 * 删除多条数据
	 * @param list
	 */
	public void deleteBatch(MongoCollection<Document> collcetion,BasicDBObject list);
	
}
