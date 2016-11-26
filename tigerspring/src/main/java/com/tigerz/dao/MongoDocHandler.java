package com.tigerz.dao;

import org.bson.Document;

/**
 * 在MongoDao中，有findAll方法，通过该接口实现回调函数用法
 *
 * @author Wang Jingci
 * 2016年11月4日 上午11:42:56
 */
public interface MongoDocHandler {
	
	public void useOneDoc(Document doc) ;


}
