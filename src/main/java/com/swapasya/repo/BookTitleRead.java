package com.swapasya.repo;

import com.mongodb.*;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.CountOptions;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	public MongoCursor<Document> findByBookName(String _bookName) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(regex(bookName, Pattern.compile(_bookName, Pattern.CASE_INSENSITIVE))).projection(projectionBasicProperties).iterator();
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
	
	
	public MongoCursor<Document> findByBookId2(String _bookId) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(eq(books_bookID, _bookId)).projection(projectionBasicProperties).iterator();
	}
	
	/**
	 * @return BookTitle with projected properties of bookTitleID, bookName, author, publication
	 */
	@Override
	public Document findByBookTitleId(String _bookTitleId) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(eq(bookTitleID, _bookTitleId)).projection(projectionBasicProperties).first();
	}
	
	public MongoCursor<Document> findByBookTitleId2 (String _bookTitleId) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(eq(bookTitleID, _bookTitleId)).projection(projectionBasicProperties).iterator();
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
	
	
	// just for equal ignore case
	private MongoCursor<Document>  findByBookNameAndAuthor(String _bookName, String _author) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);

		return collection.find(and (regex(bookName, Pattern.compile(_bookName, Pattern.CASE_INSENSITIVE)) , regex(author, Pattern.compile(_author, Pattern.CASE_INSENSITIVE))))
				.projection(new Document(bookTitleID, 1)).iterator();

	}
	
	// just for equal ignore case
	public Document  findByBookNameAndAuthorAndPub (String _bookName, String _author, String _publication) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);

		return collection.find(and (regex(bookName, Pattern.compile(_bookName, Pattern.CASE_INSENSITIVE)) , regex(author, Pattern.compile(_author, Pattern.CASE_INSENSITIVE)),
				regex(publication, Pattern.compile(_publication, Pattern.CASE_INSENSITIVE))))
				.projection(new Document(bookTitleID, 1)).first();

	}
	
	
	
	// Write Operations
	
		// Crude preq

	@Override
	public void insertOne(Document bookTitle) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		collection.insertOne(bookTitle);
	}
	
	/**
	 * 
	 * @param _personID
	 * @param _issuedType
	 * @return FindIterable<Document> : list of bookIDs issued through _issuedType which are in possession of _personID 
	 */
	public FindIterable<Document> bkIssuedTo (String _personID, String _issuedType) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(and (eq(books_borrowedBy, _personID), eq(books_issuedType, _issuedType))).projection(new Document (books_bookID, 1));
		
	}
	
	/**
	 * 
	 * @param _personID
	 * @param _issuedType
	 * @return long : number of books issued to _personID through _issuedType issue.
	 */
	public long countBkIssuedTo (String _personID, String _issuedType) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.count(and (eq(books_borrowedBy, _personID), eq(books_issuedType, _issuedType)));
		
	}
	
	
	public long countBkIssuedToCategoryWiseNormalIssue (String _personID, String _categoryType) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.count(and (eq(books_borrowedBy, _personID),eq(books_issuedType, "NormalIssue"), eq(books_categoryType, _categoryType)));
		
	}
	
	
	public long countAllBkIssuedTo (String _personID) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.count(eq(books_borrowedBy, _personID));
		
	}
	
	public long countBooksInGivenTitle (String _bookTitleID) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		
		try {
			List<Integer> i = collection.aggregate(Arrays.asList(Aggregates.project(Projections.computed("followersCount",Projections.computed("$size", "$Books")))))
					.map(follower -> follower.getInteger("followersCount")).into(new ArrayList<>());
			System.out.println(i.size() + " this is lineeeee");
			for (int ii: i) {
				System.out.println (ii + " this is actual size of array");
			}
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
		try {
		//	AggregateIterable<Document> d=  collection.aggregate(Arrays.asList(Aggregates.match(eq(bookTitleID, _bookTitleID)),Aggregates.project(Projections.fields(Projections.computed("firstCate", new Document ("$sum", Arrays.asList("categoryType")))))));
			
			AggregateIterable<Document> d=  collection.aggregate(Arrays.asList(Aggregates.match(eq(bookTitleID, _bookTitleID)),Aggregates.project(Projections.computed("firstCate",Projections.computed("$size", "$Books")))));
					List<Integer> i =		d.map(follower -> follower.getInteger("firstCate")).into(new ArrayList<>());
			System.out.println(i.size() + " this is lineeeee33");
			for (int ii: i) {
				System.out.println (ii + " this is actual size of array");
				return ii;
			}
			for (Document dd: d) {
				System.out.println(dd.toJson());
			}
			
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
		try {
			AggregateIterable<Document> d= collection.aggregate(Arrays.asList(Aggregates.match(eq(bookTitleID, _bookTitleID)), Aggregates.group("$Books", Accumulators.sum("count", 1))));
			List<Integer> i =		d.map(follower -> follower.getInteger("count")).into(new ArrayList<>());
			System.out.println(i.size() + " this is lineeeee44");
			for (int ii: i) {
				System.out.println (ii + " this is oneeeeee44");
			}
			
			for (Document dd: d) {
				System.out.println(dd.toJson() + "  in list");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	//	System.out.println(D.);
		
	}

	public long countWaitListedPersons (String _bookTitleID) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.aggregate(Arrays.asList(Aggregates.match(eq(bookTitleID, _bookTitleID)),Aggregates.project(Projections
				.computed("firstCate",Projections.computed("$size", "$WaitList")))))
				.map(follower -> follower.getInteger("firstCate")).into(new ArrayList<>()).get(0);
		
	}
	
	
	public long countAvailableBooksInBookTitle (String _bookTitleID) {
		System.out.println("in count available .....");
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		
		try {
			int count= collection.aggregate(Arrays.asList(Aggregates.match(eq(bookTitleID, _bookTitleID)),Aggregates.project(
					new Document (books, new Document ("$filter", new Document ("input", "$Books").append("as", "bookc")
							.append("cond", new Document ("$eq", Arrays.asList("$$Books.borrowedBy",null))))))))
					.map(follower -> follower.getInteger("bookc")).into(new ArrayList<>()).get(0);
			
			System.out.println(count);
			
		} catch (Exception e ) {
			e.printStackTrace();
		}
		

		Document d =  collection.find(and (eq(bookTitleID, _bookTitleID), eq(books_borrowedBy, null)))
								.projection(new Document ("Books.$",1)).first();
		
		System.out.println(d.toJson());
		
		return ((ArrayList<Document>) d.get(books)).size();
		
		
	}
	
	public long countAssignListedPersons (String _bookTitleID) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.aggregate(Arrays.asList(Aggregates.match(eq(bookTitleID, _bookTitleID)),Aggregates.project(Projections
				.computed("firstCate",Projections.computed("$size", "$AssignList")))))
				.map(follower -> follower.getInteger("firstCate")).into(new ArrayList<>()).get(0);
		
	}
	
	public void clearWaitList (String _bookTitleID) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		collection.updateOne(eq(bookTitleID, _bookTitleID), new Document ("$set", new Document (waitList, null)));
	}
	
	public void clearAssignList (String _bookTitleID) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		collection.updateOne(eq(bookTitleID, _bookTitleID), new Document ("$set", new Document (assignList, null)));
	}
	
	public void updateBookTitle (Document doc) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		collection.updateOne(eq(bookTitleID, doc.getString(bookTitleID)), new Document ("$set", doc));
	}
	
	
	/**
	 * 
	 * @param _bookID
	 * @return personID who has got this book
	 */
	public String personWhomeBookIssued (String _bookID) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		try {
			return ((ArrayList<Document>) collection.find(new Document(books_bookID,_bookID)).projection(new Document ("Books.$.borrowedBy",1)).first().get("Books")).get(0).getString(BookProp.borrowedBy);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public Document readForIssueBook (String _bookID) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		try {
			return collection.find(new Document(books_bookID,_bookID)).projection(new Document ("Books.$.borrowedBy",1).append("Books.$.categoryType", 1)).first();
		} catch (Exception e) {
			return null;
		}
	}
	
	public void issueBookToPerson (String _bookID, String _personID, String _issuedType, Date _issueDate, Date _expectedReturnDate) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
	//	collection.updateOne(eq(books_bookID,_bookID), new Document ("$push", new Document ("Books.$.")));
		
		collection.updateOne(new Document (books_bookID, _bookID),  new Document ("$set", new Document ("Books.$.borrowedBy", _personID)
				.append("Books.$.issuedType", _issuedType)));
		
	}
	
	
	public void issueBookToPersonACID (String _bookID, String _personID, String _issuedType, Date _issueDate, Date _expectedReturnDate) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);

		Document updateBk2 = new Document ("Books.$.borrowedBy", _personID).append("Books.$.issuedType", _issuedType);
		Document pullAssignList2 = new Document (assignList, new Document(s_personID, _personID));
		
		collection.updateOne(new Document (books_bookID, _bookID), new Document ("$set",updateBk2).append("$pull", pullAssignList2));
		
	}
	
	public void returnBookACID (String _bookID) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		
		Document updateBk2 = new Document ("Books.$.borrowedBy", null).append("Books.$.issuedType", null);
		Document bt1 =collection.find(eq(books_bookID, _bookID)).projection(new Document (waitList_personID, 1)).sort(new Document(waitList_timestamp, 1)).first();
		Document toRemove = new Document(s_personID, bt1.getString(waitList_personID));
		
		collection.updateOne(new Document (books_bookID, _bookID), new Document ("$set",updateBk2).append("$pull", new Document (waitList, toRemove))
				.append("$push", new Document (assignList, toRemove)));
	}
	
	public Document findByBookIdForReturnAndTransactionHistory (String _bookId) {
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(eq(books_bookID, _bookId)).projection(new Document (bookTitleID,1).append(bookName, 1).append(author, 1)
				.append(publication, 1).append("Books.$.expectedReturnDate",1).append("Books.$.borrowedBy", 1)
				.append("Books.$.issuedType", 1).append("Books.$.categoryType", 1)).first();
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
	
	public void removeBookFromBookTitle (String _bookTitleID, String _bookID ) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		collection.updateOne(eq("_id", _bookTitleID), new Document("$pull", new Document ("Books", new Document (BookProp.bookID, _bookID))));
		
	}
	
	
	public String findInWaitList(String _personID, String _bookID) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);
		
		// whether person is valid or not

		Document doc = collection.find(and(eq(books_bookID, _bookID), eq(waitList_personID, _personID)))
				.projection(new Document(bookTitleID, 1)).first();
		
		if (doc==null)
			return null;
		
		return doc.getString(bookTitleID);

	}
	
	
	public String findInAssignList(String _personID, String _bookID) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);
		
		// whether person is valid or not

		Document doc = collection.find(and(eq(books_bookID, _bookID), eq(assignList_personID, _personID)))
				.projection(new Document(bookTitleID, 1)).first();
		
		if (doc==null)
			return null;
		
		return doc.getString(bookTitleID);

	}
	
	/// https://stackoverflow.com/questions/32178146/update-nested-array-list-in-mongodb-java
	
	// problem with uniqueness of field in single document itself.
	// http://joegornick.com/2012/10/25/mongodb-unique-indexes-on-single-embedded-documents/
	// problem: A book title with id BT01 can have two books of same ids BT01.1 and BT01.1 and BT01.1 and many books of same id in single document

	public boolean addInWaitList(String _personID, String _bookID) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);

		if (findInWaitList(_personID, _bookID) != null) {
			// already in waitlist
			return false;
		}

		collection.updateOne(eq(books_bookID, _bookID), new Document("$push", new Document (waitList, new Document(s_personID, _personID).append(s_timestamp, new Date().getTime()))));
		return true;

	}
	
	
	public boolean removeFromWaitList(String _personID, String _bookID) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);

		if (findInWaitList(_personID, _bookID) == null) {
			// not in waitlist, no need to remove
			return false;
		}

		collection.updateOne(eq(books_bookID, _bookID), new Document("$pull", new Document (waitList, new Document(s_personID, _personID))));
		return true;

	}
	
	public boolean removeFromAssignList(String _personID, String _bookID) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);

//		if (findInAssignList(_personID, _bookID) == null) {
//			// not in waitlist, no need to remove
//			return false;
//		}

		collection.updateOne(eq(books_bookID, _bookID), new Document("$pull", new Document (assignList, new Document(s_personID, _personID))));
		return true;

	}
	
	
	/*
	 * in one book issue we will do following
	 * 1. remove from assign list, issue book... 
	 * 2. remove another person from wait list and add to assign list
	 * 
	 * For this we need to learn transaction in mongoDB for doing this
	 * 
	 */
	
	
	

	
	

}
