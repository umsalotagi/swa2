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

	public boolean addInWaitList(String personID, String bookID) {

		MongoCollection<Document> collection = database.getCollection("BookTitle");
		
		if (findInWaitList(personID, bookID) == null) {
			return false;
		}
		
		collection.updateOne(eq("books.bookID", bookID), new Document("$push", new Document("id", personID)));
		return true;

	}

}
