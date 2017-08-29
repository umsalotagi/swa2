package com.swapasya.repo;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.swapasya.dataTypes.NameKinds.UnRegisteredPerson;
import static com.swapasya.dataTypes.PersonProp.personID;
import static com.swapasya.dataTypes.PersonProp.personName;
import static com.swapasya.dataTypes.UnRegisteredPersonProp.*;

import java.util.regex.Pattern;

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

		collection = database.getCollection(UnRegisteredPerson);
	}
	
	@Override
	public long count() {
		return collection.count();
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
	public MongoCursor<Document> findByPersonName(String _personName) {
		return collection.find(regex(personName, Pattern.compile(_personName, Pattern.CASE_INSENSITIVE)))
				.projection(projectionBasicProperties).iterator();
	}

	@Override
	public Document findByPersonId(String _personId) {
		return collection.find(eq(personID, _personId)).projection(projectionBasicProperties).first();
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
	public void insertOne(Document _UnRegisteredPerson) {
		MongoCollection<Document> collection = database.getCollection(UnRegisteredPerson);
		collection.insertOne(_UnRegisteredPerson);
		
	}

	@Override
	public void validateUnRegisteredPerson(String personID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateUnRegisteredPerson(String rollNo, String courseYear, String name) {
		// TODO Auto-generated method stub
		
	}

}
