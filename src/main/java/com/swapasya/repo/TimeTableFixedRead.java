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
import static com.swapasya.dataTypes.NameKinds.TimeTableFixed;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import org.bson.conversions.Bson;

import com.mongodb.MongoClient;


import static com.swapasya.dataTypes.TimeTableFixedProp.*;
import static com.swapasya.dataTypes.TransactionHistoryProp.fineCollected;

public class TimeTableFixedRead implements TimeTableFixedReadIn {
	
	
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	//static Bson projectionBasicProperties = fields(include(bookTitleID	, bookName, author, publication));

	public TimeTableFixedRead (String databaseName) {
		MongoClient mongoClient = new MongoClient();
		if (databaseName==null) {
			database = mongoClient.getDatabase("local");
			collection= database.getCollection(TimeTableFixed);
		} else {
			database = mongoClient.getDatabase(databaseName);
			collection= database.getCollection(TimeTableFixed);
		}
		
	
		
	}

	@Override
	public void addEvent(Document repeatativeEvent) {
		// TODO Auto-generated method stub
		 collection.insertOne(repeatativeEvent);
	}

	@Override
	public void removeEvent(String timeTableName, String teacherID, String subject, String classRoomID) {
		// TODO Auto-generated method stub
		collection.deleteOne(and(eq("timeTableName",timeTableName),eq("byTeacherID",timeTableName),eq("subject",subject),eq("classRoomID",classRoomID)));
	}

	@Override
	public void removeEvent(String id) {
		// TODO Auto-generated method stub
		collection.deleteOne(eq("timeTableName",timeTableName));
	}

	@Override
	public void addLectureInEvent(String timeTableName, String teacherID, String subject, String classRoomID,
			Document lecturePactical) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLectureInEvent(String timeTableName, String teacherID, String subject, String classRoomID,
			Document lecturePactical) {
		// TODO Auto-generated method stub
		
	}

	

}
