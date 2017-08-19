package com.swapasya.repo;


import org.bson.Document;
import com.mongodb.client.MongoCursor;

public interface BookTitleReadIn {
	
// Read Operations
	
	// Crude preq
	long count();
	void deleteAll();
	void delete(String bookTitleId);
	void delete(Document bookTitle);
	boolean exists(String bookTitleId);
	MongoCursor<Document> findAll();
	
	
	// user defined
	
	MongoCursor<Document> findByBookName(String bookTitle);
	MongoCursor<Document> findByTag(String tag);
	MongoCursor<Document> findByAuthor(String author);
	MongoCursor<Document> findByPublication(String publication);
	Document findByBookId(String bookId);
	Document findByBookTitleId(String bookTitleId);
	
	Document findByBookIdFULL(String bookId);
	Document findByBookTitleIdFULL(String bookTitleId);
	
	
	
// Write Operations
	
	// Crude preq
	void insertOne(Document bookTitle);
	
	
	// user defined

}
