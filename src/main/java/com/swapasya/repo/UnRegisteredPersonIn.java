package com.swapasya.repo;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

public interface UnRegisteredPersonIn {
	// Read Operations
	
		// Crude preq
		long count();
		void deleteAll();
		void delete(String personID);
		void delete(Document Person);
		boolean exists(String personID);
		MongoCursor<Document> findAll();
		
		
		// user defined
		
		MongoCursor<Document> findByPersonName(String personName);
		
		Document findByPersonId(String personId);

	//	MongoCursor<Document> setFilterFindAll ();
		
		MongoCursor<Document> setFilterAndFindByPersonName(String personName);
		
		
	// Write Operations
		
		// Crude preq
		void insertOne(Document person);
		
		
		// user defined
}
