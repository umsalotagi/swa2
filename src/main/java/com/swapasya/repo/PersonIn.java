package com.swapasya.repo;




import org.bson.Document;
import com.mongodb.client.MongoCursor;

public interface PersonIn {
	
// Read Operations
	
	// Crude preq
	long count();
	void deleteAll();
	void delete(String personID);
	void delete(Document Person);
	boolean exists(String personID);
	MongoCursor<Document> findAll();
	
	
	// user defined
	
	/*MongoCursor<Document> findByPersonId(String personID);*/
	MongoCursor<Document> findByPersonName(String personName);
	
	Document findByPersonId(String personId);
	//Document findByPersonName(String personName);
	
	//Document findByBookIdFULL(String bookId);
	//Document findByBookTitleIdFULL(String bookTitleId);
	
	
	
// Write Operations
	
	// Crude preq
	void insertOne(Document person);
	
	
	// user defined

}
