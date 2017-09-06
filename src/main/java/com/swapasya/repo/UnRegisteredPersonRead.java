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

public class UnRegisteredPersonRead {
	
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	static Bson projectionBasicProperties = fields(include(unRegisteredPersonID, personName, degree, branch, courseyear, division, rollNo, role , readerType));

	public UnRegisteredPersonRead(String databaseName) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		if (databaseName == null) {
			database = mongoClient.getDatabase("local");

		} else {
			database = mongoClient.getDatabase(databaseName);
		}

		collection = database.getCollection(UnRegisteredPerson);
	}
	
	
	public long count() {
		return collection.count();
	}

	
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(String personID) {
		collection.deleteOne(eq("_id", personID));
		
	}
	
	public void delete(String _personName, String _parentName) {
		collection.deleteOne(and(eq(personName, _personName), eq (parentName, _parentName)));
		
	}

	
	public void delete(Document Person) {
		// TODO Auto-generated method stub
		
	}

	
	public boolean exists(String personID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public MongoCursor<Document> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public MongoCursor<Document> findByPersonName(String _personName) {
		return collection.find(regex(personName, Pattern.compile(_personName, Pattern.CASE_INSENSITIVE)))
				.projection(projectionBasicProperties).iterator();
	}
	
	public Document findByNameAndRollNoFULL (String _personName, String _parentName) {
		return collection.find(and(eq(personName, _personName), eq (parentName, _parentName))).first();
	}

	
	public Document findByPersonId(String _personId) {
		return collection.find(eq(unRegisteredPersonID, _personId)).projection(projectionBasicProperties).first();
	}
	
	public MongoCursor<Document> findByPersonId2(String _personId) {
		return collection.find(eq(unRegisteredPersonID, _personId)).projection(projectionBasicProperties).iterator();
	}
	
	public Document findByPersonIdFULL (String _personId) {
		return collection.find(eq(unRegisteredPersonID, _personId)).first();
	}
	
	public void updatePerson (Document doc) {
		collection.updateOne(eq(unRegisteredPersonID, doc.getString(unRegisteredPersonID)), new Document ("$set", doc));
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

	
	public void insertOne(Document _UnRegisteredPerson) {
		collection.insertOne(_UnRegisteredPerson);
		
	}

	
}
