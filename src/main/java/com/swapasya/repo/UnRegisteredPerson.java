package com.swapasya.repo;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.swapasya.dataTypes.NameKinds.UnRegisteredPerson;
import static com.swapasya.dataTypes.PersonProp.branch;
import static com.swapasya.dataTypes.PersonProp.courseyear;
import static com.swapasya.dataTypes.PersonProp.degree;
import static com.swapasya.dataTypes.PersonProp.division;
import static com.swapasya.dataTypes.PersonProp.personID;
import static com.swapasya.dataTypes.PersonProp.personName;
import static com.swapasya.dataTypes.PersonProp.readerType;
import static com.swapasya.dataTypes.PersonProp.role;
import static com.swapasya.dataTypes.UnRegisteredPersonProp.*;

import java.util.ArrayList;
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
	
	public void delete(String _personName, String _parentName) {
		collection.deleteOne(and(eq(personName, _personName), eq (parentName, _parentName)));
		
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
	
	public Document findByNameAndRollNoFULL (String _personName, String _parentName) {
		return collection.find(and(eq(personName, _personName), eq (parentName, _parentName))).first();
	}

	@Override
	public Document findByPersonId(String _personId) {
		return collection.find(eq(personID, _personId)).projection(projectionBasicProperties).first();
	}
	
	public Document findByPersonIdFULL (String _personId) {
		return collection.find(eq(personID, _personId)).first();
	}


	
	public MongoCursor<Document> setFilterFindAll(String _degree, String _branch, String _courseyear,  String _division, String _rolNo) {

			
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
			
			if (_division!=null) {
				Bson c=(Bson) eq(division,_division);
				filters.add(c);
			}
			
			return  collection.find(and(filters)).iterator();

	}


	public MongoCursor<Document> setFilterAndFindByPersonName(String _degree, String _branch, String _courseyear,  String _division, String _rolNo, String _personName) {
		
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
		
		if (_division!=null) {
			Bson c=(Bson) eq(division,_division);
			filters.add(c);
		}
		
		filters.add(regex(personName, Pattern.compile(_personName, Pattern.CASE_INSENSITIVE)));
		
		return  collection.find(and(filters)).iterator();
	}

	@Override
	public void insertOne(Document _UnRegisteredPerson) {
		MongoCollection<Document> collection = database.getCollection(UnRegisteredPerson);
		collection.insertOne(_UnRegisteredPerson);
		
	}

	
}
