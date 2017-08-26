package com.swapasya.repo;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;
import com.swapasya.dataTypes.BookProp;
import com.swapasya.dataTypes.NameKinds;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import static com.swapasya.dataTypes.RulesProp.*;
import static com.swapasya.dataTypes.NameKinds.*;


public class LibraryRulesRead implements LibraryRulesReadIn{
	
	
	MongoDatabase database;
	
	public LibraryRulesRead(String databaseName) {
		MongoClient mongoClient = new MongoClient();
		if (databaseName==null) {
			database = mongoClient.getDatabase("local");
		} else {
			database = mongoClient.getDatabase(databaseName);
		}
		
		
	}


	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String _readerType, String _bkCatORissueType) {
		MongoCollection<Document> collection = database.getCollection(LibraryRules);
		collection.deleteOne(and(eq(readerType, _readerType), eq(bkCatORissueType, _bkCatORissueType)));
	}

	@Override
	public boolean exists(String _readerType, String _bkCatORissueType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MongoCursor<Document> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document findRulesFor(String _readerType, String _bkCatORissueType) {
		MongoCollection<Document> collection = database.getCollection(LibraryRules);
		return collection.find(and(eq(readerType, _readerType), eq(bkCatORissueType, _bkCatORissueType))).first();
	}


	@Override
	public void addRules(Document libraryRule) {
		if (findRulesFor(libraryRule.getString(readerType), libraryRule.getString(bkCatORissueType)) == null) {
			// No duplicate rules
			MongoCollection<Document> collection = database.getCollection(LibraryRules);
			collection.insertOne(libraryRule);
			
		}
		
	}


}
