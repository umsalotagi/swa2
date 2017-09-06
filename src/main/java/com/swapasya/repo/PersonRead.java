package com.swapasya.repo;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.swapasya.dataTypes.BookTitleProp.bookTitleID;
import static com.swapasya.dataTypes.NameKinds.BookTitle;
import static com.swapasya.dataTypes.NameKinds.Person;
import static com.swapasya.dataTypes.PersonProp.*;
import static com.swapasya.dataTypes.TransactionHistoryProp.dateOfIssue;
import static com.swapasya.dataTypes.TransactionHistoryProp.issuetype;

import java.util.ArrayList;
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

	static Bson projectionBasicProperties = fields(include(personID, personName, degree, branch, courseyear, division, rollNo, role , readerType));
	
	static Bson projectionBasicForLibrary = fields(include(personID, personName, degree, branch, courseyear, role));

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
	
	public long countPersonsIn (String _degree, String _branch, String _courseyear, String _role, String _readerType, String _division) {
		
		ArrayList <Bson> filters= new ArrayList<Bson>();
		
		if (_degree!=null) {
			Bson c=(Bson) eq(degree,_degree);
			filters.add(c);
		}
		
		if (_branch!=null) {
			Bson c=(Bson) eq(branch,_branch);
			filters.add(c);
		}
		
		if (_courseyear!=null) {
			Bson c=(Bson) eq(courseyear,_courseyear);
			filters.add(c);
		}
		
		if (_role!=null) {
			Bson c=(Bson) eq(role,_role);
			filters.add(c);
		}
		
		if (_readerType!=null) {
			Bson c=(Bson) eq(readerType,_readerType);
			filters.add(c);
		}
		
		if (_division!=null) {
			Bson c=(Bson) eq(division,_division);
			filters.add(c);
		}
		
		return  collection.count(and(filters));
		
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
	
	public Document findByPersonIdFULL(String _personId) {
		return collection.find(eq(personID, _personId)).first();
	}
	
	public MongoCursor<Document> findByPersonId2 (String _personId) {
		return collection.find(eq(personID, _personId)).projection(projectionBasicProperties).iterator();
	}

	@Override
	public MongoCursor<Document> findByPersonName(String _personName) {
		return collection.find(regex(personName, Pattern.compile(_personName, Pattern.CASE_INSENSITIVE)))
				.projection(projectionBasicProperties).iterator();
	}
	
	public void updatePerson (Document doc) {
		collection.updateOne(eq(personID, doc.getString(personID)), new Document ("$set", doc));
	}

	public String getPersonRole(String _personId) {
		return collection.find(eq(personID, _personId)).projection(new Document(role, 1)).first().getString(role);
	}
	
	public String getReaderType(String _personId) {
		return collection.find(eq(personID, _personId)).projection(new Document(readerType, 1)).first().getString(readerType);
	}

	@Override
	public void insertOne(Document person) {
		collection.insertOne(person);
	}

}
