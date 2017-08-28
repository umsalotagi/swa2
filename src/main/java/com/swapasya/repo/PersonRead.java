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

public class PersonRead implements PersonReadIn {
	MongoDatabase database;
	MongoCollection<Document> collection;

	static Bson projectionBasicProperties = fields(include(personID, personName, rollNo, branch, address));

	public PersonRead(String databaseName) {
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
		return collection.count();
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String _personId) {
		collection.deleteOne(eq(personID, _personId));
	}

	@Override
	public void delete(Document Person) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(String _personID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MongoCursor<Document> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document findByPersonId(String _personId) {
		return collection.find(eq(personID, _personId)).projection(projectionBasicProperties).first();
	}

	@Override
	public MongoCursor<Document> findByPersonName(String _personName) {
		return collection.find(regex(personName, Pattern.compile(_personName, Pattern.CASE_INSENSITIVE)))
				.projection(projectionBasicProperties).iterator();
	}

	public String getPersonRole(String _personId) {
		return collection.find(eq(personID, _personId)).projection(new Document(role, 1)).first().getString(role);
	}

	@Override
	public void insertOne(Document person) {
		collection.insertOne(person);
	}

}
