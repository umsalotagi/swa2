package com.swapasya.repo;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.swapasya.dataTypes.NameKinds.Person;
import static com.swapasya.dataTypes.PersonProp.*;

import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class PersonRead implements PersonIn 
{
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	static Bson projectionBasicProperties = fields(include(personID,personName,rollNo,branch,address));

	public PersonRead(String databaseName) {
		MongoClient mongoClient = new MongoClient("localhost",27017);
		 
		if (databaseName==null) {
			database = mongoClient.getDatabase("local");
			collection= database.getCollection(Person);
		} else {
			database = mongoClient.getDatabase(databaseName);
			collection= database.getCollection(Person);
		}
		
	}


	@Override
	public long count() {
		// TODO Auto-generated method stub
		long cnt=collection.count();
		System.out.println(cnt);
		return cnt;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String personId) {
		// TODO Auto-generated method stub
		collection.deleteOne(eq(personID, personId));		
	}

	@Override
	public void delete(Document Person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(String personID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MongoCursor<Document> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document findByPersonId(String personId) {
		// TODO Auto-generated method stub
		return collection.find(eq(personID, personId)).projection(projectionBasicProperties).first();
	}

	@Override
	public MongoCursor<Document> findByPersonName(String _personName) {
		// TODO Auto-generated method stub
		return collection.find(regex(personName, Pattern.compile(_personName, Pattern.CASE_INSENSITIVE))).projection(projectionBasicProperties).iterator();
	}

	@Override
	public void insertOne(Document person) {
		// TODO Auto-generated method stub
		collection.insertOne(person);
		
	}

}
