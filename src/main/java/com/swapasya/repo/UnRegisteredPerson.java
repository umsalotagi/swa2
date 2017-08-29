package com.swapasya.repo;

import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.swapasya.dataTypes.NameKinds.Person;
import static com.swapasya.dataTypes.UnRegisteredPersonProp.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class UnRegisteredPerson implements UnRegisteredPersonIn{
	
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	static Bson projectionBasicProperties = fields(include(personID, personName, degree, branch, courseyear, division, rollNo, role , readerType));

	public UnRegisteredPerson(String databaseName) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		if (databaseName == null) {
			database = mongoClient.getDatabase("local");

		} else {
			database = mongoClient.getDatabase(databaseName);
		}

		collection = database.getCollection(Person);
	}
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String personID) {
		// TODO Auto-generated method stub
		
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
	public MongoCursor<Document> findByPersonName(String personName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document findByPersonId(String personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MongoCursor<Document> setFilterFindAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MongoCursor<Document> setFilterAndFindByPersonName(String personName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertOne(Document person) {
		// TODO Auto-generated method stub
		
	}

}
