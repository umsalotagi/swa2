package com.swapasya.repo;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;
import com.swapasya.dataTypes.BookProp;
import com.swapasya.dataTypes.NameKinds;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import static java.util.Arrays.asList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;

import static com.swapasya.dataTypes.BookTitleProp.*;
import static com.swapasya.dataTypes.NameKinds.*;


public class BookTitleRead implements BookTitleReadIn {

	MongoDatabase database;
	
	static Bson projectionBasicProperties = fields(include(bookTitleID	, bookName, author, publication));

	public BookTitleRead(String databaseName) {
		MongoClient mongoClient = new MongoClient();
		if (databaseName==null) {
			database = mongoClient.getDatabase("local");
		} else {
			database = mongoClient.getDatabase(databaseName);
		}
		
		
	}

	// Mandatory basic bookTitleRelated repositories
	
	

	
	

	
	// Read Operations
	
		// Crude preq

	@Override
	public long count() {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.count();
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String _bookTitleId) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		collection.deleteOne(eq(bookTitleID, _bookTitleId));
		
	}

	@Override // NO NEED
	public void delete(Document bookTitle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(String bookTitleId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	// user defined

	@Override
	public MongoCursor<Document> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @return BookTitle with projected properties of bookTitleID, bookName, author, publication
	 */
	@Override
	public MongoCursor<Document> findByBookName(String bookTitle) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(regex(bookName, Pattern.compile(bookTitle, Pattern.CASE_INSENSITIVE))).projection(projectionBasicProperties).iterator();
	}

	@Override
	public MongoCursor<Document> findByTag(String _tag) {
		// still need to do equal ignore case
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(eq(tags, _tag)).projection(projectionBasicProperties).iterator();
	}

	@Override
	public MongoCursor<Document> findByAuthor(String _author) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(regex(author, Pattern.compile(_author, Pattern.CASE_INSENSITIVE))).projection(projectionBasicProperties).iterator();

	}
	
	/**
	 * @return BookTitle with projected properties of bookTitleID, bookName, author, publication
	 */
	@Override
	public MongoCursor<Document> findByPublication(String _publication) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(regex(publication, Pattern.compile(_publication, Pattern.CASE_INSENSITIVE))).projection(projectionBasicProperties).iterator();
	}

	
	/**
	 * @return BookTitle with projected properties of bookTitleID, bookName, author, publication
	 */
	@Override
	public Document findByBookId(String _bookId) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(eq(books_bookID, _bookId)).projection(projectionBasicProperties).first();
	}
	
	/**
	 * @return BookTitle with projected properties of bookTitleID, bookName, author, publication
	 */
	@Override
	public Document findByBookTitleId(String _bookTitleId) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(eq(bookTitleID, _bookTitleId)).projection(projectionBasicProperties).first();
	}
	
	/**
	 * @return FULL BookTitle Document which has every property, books, waitList and asignList. USE IT ONLY WHEN NECESSARY
	 */
	@Override
	public Document findByBookIdFULL(String _bookId) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(eq(books_bookID, _bookId)).first();
	}

	/**
	 * @return FULL BookTitle Document which has every property, books, waitList and asignList. USE IT ONLY WHEN NECESSARY
	 */
	@Override
	public Document findByBookTitleIdFULL(String _bookTitleId) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(eq(bookTitleID, _bookTitleId)).first();
	}
	
	
	
	// Write Operations
	
		// Crude preq

	@Override
	public void insertOne(Document bookTitle) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		collection.insertOne(bookTitle);
	}
	
	
	public void addBookToBookTitle (String _bookTitleID, Document book) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		
		Document doc = collection.find(eq(books_bookID, book.getString(BookProp.bookID))).projection(new Document(bookTitleID, 1)).first();
		if (doc!=null && doc.getString(bookTitleID)!=null) {
			// Duplicate entry
			return;			
		}
		
		collection.updateOne(eq("_id", _bookTitleID), new Document("$push", new Document ("Books", book)));
		
	}
	
	public void removeBookFromBookTitle (String _bookTitleID, String bookID ) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		collection.updateOne(eq("_id", _bookTitleID), new Document("$pull", new Document ("Books", new Document (BookProp.bookID, bookID))));
		
	}
	
	
	private String findInWaitList(String personID, String bookID) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);

		Document doc = collection.find(and(eq(books_bookID, bookID), eq(waitList_personID, personID)))
				.projection(new Document(bookTitleID, 1)).first();
		
		if (doc==null)
			return null;
		
		return doc.getString(bookTitleID);

	}
	
	/// https://stackoverflow.com/questions/32178146/update-nested-array-list-in-mongodb-java
	
	// problem with uniqueness of field in single document itself.
	// http://joegornick.com/2012/10/25/mongodb-unique-indexes-on-single-embedded-documents/
	// problem: A book title with id BT01 can have two books of same ids BT01.1 and BT01.1 and BT01.1 and many books of same id in single document

	public boolean addInWaitList(String personID, String bookID) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);

		if (findInWaitList(personID, bookID) != null) {
			// already in waitlist
			return false;
		}

		collection.updateOne(eq(books_bookID, bookID), new Document("$push", new Document (waitList, new Document(waitList_personID, personID).append(waitList_timestamp, new Date().getTime()))));
		return true;

	}
	
	
	public boolean removeFromWaitList(String personID, String bookID) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);

		if (findInWaitList(personID, bookID) == null) {
			// not in waitlist, no need to remove
			return false;
		}

		collection.updateOne(eq(books_bookID, bookID), new Document("$pull", new Document (waitList, new Document(waitList_personID, personID))));
		return true;

	}
	
	
	/*
	 * in one book issue we will do following
	 * 1. remove from assign list, issue book... 
	 * 2. remove another person from wait list and add to assign list
	 * 
	 * For this we need to lean transaction in mongoDB for doing this
	 * 
	 */
	

}
