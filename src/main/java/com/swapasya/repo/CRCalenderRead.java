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
import static com.swapasya.dataTypes.NameKinds.CRCalendar;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import java.util.Date;
import java.util.List;

import com.mongodb.MongoClient;
import static com.swapasya.dataTypes.CRCalenderProp.*;
import static com.swapasya.dataTypes.NameKinds.BookTitle;


public class CRCalenderRead implements CRCalenderReadIn {
	
	MongoDatabase database;
	
	//static Bson projectionBasicProperties = fields(include(bookTitleID	, bookName, author, publication));

	public CRCalenderRead(String databaseName) {
		MongoClient mongoClient = new MongoClient();
		if (databaseName==null) {
			database = mongoClient.getDatabase("local");
		} else {
			database = mongoClient.getDatabase(databaseName);
		}
		
		
	}

	@Override
	public MongoCursor<Document> showTodaysCalenderFor(String personID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MongoCursor<Document> showWeekCalenderFor(String personID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void presentyFor(String eventID, List<String> personID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createCalenderFromFixedTimeTable(Document repeatativeEvent, Date eventStartDate, Date eventEndDate) {
		// TODO Auto-generated method stub
		MongoCollection<Document> collection = database.getCollection(CRCalendar);
//		repeatativeEvent.append(key, value);
		collection.insertOne(repeatativeEvent);
	}

	@Override
	public void addHoliday(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createRegularCalenderFor(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEvent(String eventID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEvent(List<String> eventID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEvent(String teacherID, String subject, String classRoomID, Date date, Date from, Date to) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createCalenderFromFixedTimeTable(String timeTableName) {
		// TODO Auto-generated method stub
		
	}

}
