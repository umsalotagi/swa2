package com.swapasya.repo;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

public interface LibraryRulesReadIn {
	
	// Read Operations
	
	// Crude preq
	void deleteAll();
	void delete(String _readerType, String _bkCatORissueType);
	boolean exists(String _readerType, String _bkCatORissueType);
	MongoCursor<Document> findAll();
	
	
	// user defined
	Document findRulesFor (String _readerType, String _bkCatORissueType);
	void addRules (Document libraryRules);

}
