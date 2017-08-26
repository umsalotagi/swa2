package com.swapasya.repo;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
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
	
	
	// just for equal ignore case
	private MongoCursor<Document>  findByBookNameAndAuthor(String _bookName, String _author) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);

		return collection.find(and (regex(bookName, Pattern.compile(_bookName, Pattern.CASE_INSENSITIVE)) , regex(author, Pattern.compile(_author, Pattern.CASE_INSENSITIVE))))
				.projection(new Document(bookTitleID, 1)).iterator();

	}
	
	// just for equal ignore case
	private Document  findByBookNameAndAuthorAndPub (String _bookName, String _author, String _publication) {

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
	
	public FindIterable<Document> bkIssuedTo (String _personID, String _issuedType) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		return collection.find(and (eq(books_borrowedBy, _personID), eq(books_issuedType, _issuedType))).projection(new Document (books_bookID, 1));
		
	}
	
	public String isBookAvailable (String _bookID) {
		
		MongoCollection<Document> collection = database.getCollection(BookTitle);
		try {
			return collection.find(eq(books_bookID,_bookID)).projection(new Document (books_borrowedBy, 1)).first().getString(books_borrowedBy);
		} catch (Exception e) {
			return null;
		}
		
		
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
	
	
	private String findInWaitList(String _personID, String _bookID) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);

		Document doc = collection.find(and(eq(books_bookID, _bookID), eq(waitList_personID, _personID)))
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

		collection.updateOne(eq(books_bookID, _bookID), new Document("$push", new Document (waitList, new Document(waitList_personID, _personID).append(waitList_timestamp, new Date().getTime()))));
		return true;

	}
	
	
	public boolean removeFromWaitList(String _personID, String _bookID) {

		MongoCollection<Document> collection = database.getCollection(BookTitle);

		if (findInWaitList(_personID, _bookID) == null) {
			// not in waitlist, no need to remove
			return false;
		}

		collection.updateOne(eq(books_bookID, _bookID), new Document("$pull", new Document (waitList, new Document(waitList_personID, _personID))));
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
	
	public void issueBook (String _bookID, String _personID) {
		issueBook(_bookID, _personID, "NormalIssue");
	}
	
	public void issueBook (String _bookID, String _personID, String issueType) {
		
		
		
	}
	
	
	
	public void readCSVFile (String pathToFile) throws NumberFormatException, IOException{
		File file = getFile("BookCatalogue.csv");

		BufferedReader bir = new BufferedReader(new FileReader(file));
		String rowStr = null;

		while ((rowStr = bir.readLine()) != null) {
			//System.out.println(rowStr);

			String row[] = rowStr.split(",");

			String bookID = row[0].trim();
			if (bookID.equals("BookID")) {
				continue;
			}

			String isbnNumber = row[1].trim();
			String bookName = row[2].trim();
			String authour = row[3].trim();
			String publication = row[4].trim();
			String bindingType = row[5].trim();
			String priceStr = row[6].trim();
			String noOfPagesStr = row[7].trim();
			String language = row[8].trim();
			String categoryType = row[9].trim();
			//System.out.println(isbnNumber + " " +categoryType + " :" + row [10]);
			
			Date purchaseDate = null ; // = row [11];
			String[] tagsL = null;
			ArrayList <String> tags = new ArrayList <String>();
			try {
				tagsL = row[11].trim().split(";");
				for (String l:tagsL) {
					tags.add(l);
				}
			} catch (Exception e) {
				
			}

			if (priceStr.isEmpty() || priceStr == "") {
				priceStr = "0";
			}

			if (noOfPagesStr.isEmpty() || noOfPagesStr.equals("")) {
				noOfPagesStr = "0";
			}

			float price = Integer.parseInt(priceStr);
			int noOfPages = Integer.parseInt(noOfPagesStr);

			addBookCSV(bookID, isbnNumber, bookName, authour, categoryType, publication, price, purchaseDate,
					bindingType, tags, noOfPages, language, null, null);

		}
	}
	
	private File getFile(String fileName){

		ClassLoader classLoader = getClass().getClassLoader();
		System.out.println(classLoader);
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
	
	public void addBookCSV(String _bookID, String _isbnNumber, String _bookName, String _author, String _categoryType,
			String _publication, float _price, Date _purchaseDate, String _bindingType, ArrayList<String> _tags,
			int _noOfPages, String _language, String _location, String _imgPath) {
		
		Document bt = findByBookNameAndAuthorAndPub(_bookName, _author, _publication);
		
		if (bt == null ) {
			// No dup book found
			String _bookTitleID = "T-"+_bookID; // change this way
			
			Document bt1 = DocumemtRepository.emptyListBookTitle(_bookTitleID, _isbnNumber, _bookName, _author, _publication, _bindingType, 
					_tags, _noOfPages, _language, _location, _imgPath);
			
			Document d = DocumemtRepository.book(_bookID, _purchaseDate, _price, _categoryType);
			
			bt1.append(books, d);
			
			insertOne(bt1);
		} else if (bt.getString(bookTitleID)!=null){
			
			Document d = DocumemtRepository.book(_bookID, _purchaseDate, _price, _categoryType);
			
			addBookToBookTitle(bt.getString(bookTitleID), d);
			
		}
		
		
	}
	
	

}
