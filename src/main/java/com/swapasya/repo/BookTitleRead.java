package com.swapasya.repo;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;

import org.bson.Document;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import static java.util.Arrays.asList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import static com.swapasya.dataTypes.BookTitleProp.*;

public class BookTitleRead {

	MongoDatabase database;

	BookTitleRead() {
		MongoClient mongoClient = new MongoClient();
		database = mongoClient.getDatabase("local");
	}

	public String findInWaitList(String personID, String bookID) {

		MongoCollection<Document> collection = database.getCollection("BookTitle");

		Document doc = collection.find(and(eq("books.bookID", bookID), eq("waitList.personID", personID)))
				.projection(new Document(bookTitleID, 1)).first();

		return doc.getString(bookTitleID);

	}
	
	/// https://stackoverflow.com/questions/32178146/update-nested-array-list-in-mongodb-java
	
	// problem with uniqueness of field in single document itself.
	// http://joegornick.com/2012/10/25/mongodb-unique-indexes-on-single-embedded-documents/
	// problem: A book title with id BT01 can have two books of same ids BT01.1 and BT01.1 and BT01.1 and many books of same id in single document

	public boolean addInWaitList(String personID, String bookID) {

		MongoCollection<Document> collection = database.getCollection("BookTitle");

		if (findInWaitList(personID, bookID) != null) {
			// already in waitlist
			return false;
		}

		collection.updateOne(eq("books.bookID", bookID), new Document("$push",
				new Document("books.id", personID).append("books.timestamp", new Date().getTime())));
		return true;

	}

}
