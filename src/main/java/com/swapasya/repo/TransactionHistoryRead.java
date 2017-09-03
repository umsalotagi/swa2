package com.swapasya.repo;


import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.swapasya.dataTypes.NameKinds.TransactionHistory;
import static com.swapasya.dataTypes.PersonProp.personName;
import static com.swapasya.dataTypes.TransactionHistoryProp.*;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

//import org.bson.BsonType;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
//import com.mongodb.client.model.Filters;
//import com.sun.net.httpserver.Filter;

public class TransactionHistoryRead implements TransactionHistoryReadIn {
	
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	static Bson projectionBasicProperties = fields(include(transactionID,personID,dateOfIssue,dateOfReturn));
	
	public TransactionHistoryRead(String databaseName) {
		MongoClient mongoClient = new MongoClient("localhost",27017);
		 
		if (databaseName==null) {
			database = mongoClient.getDatabase("local");
			collection= database.getCollection(TransactionHistory);
		} else {
			database = mongoClient.getDatabase(databaseName);
			collection= database.getCollection(TransactionHistory);
		}
		
	}
	
	public static Date dayStartTime(Date xdate){
	      Calendar cal = Calendar.getInstance();
	      cal.setTime(xdate);
	      int year=cal.get(cal.YEAR);
	      int month=cal.get(cal.MONTH);
	      int date=cal.get(cal.DATE);
	      cal.set(year, month, date,0,0,1);
	      return cal.getTime();
		}	
	
	
	public static Date dayEndTime(Date xdate){
	      Calendar cal = Calendar.getInstance();
	      cal.setTime(xdate);
	      int year=cal.get(cal.YEAR);
	      int month=cal.get(cal.MONTH);
	      int date=cal.get(cal.DATE);
	      cal.set(year, month, date,23,59,59);
	      return cal.getTime();
		}
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		long cnt=collection.count();
		System.out.println(cnt);
		return cnt;
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String transactionID) {
		// TODO Auto-generated method stub
//		collection.deleteOne(eq("_ID", transactionID));	...Not to implement
	}

	@Override
	public MongoCursor<Document> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countToday(Date x) {
		// TODO Auto-generated method stub

		 return collection.count(eq(dateOfIssue,x));
		
	}
	
	

	@Override
	public long countFromTo(Date startDate, Date endDate) {
		// TODO Auto-generated method stubi
//		ArrayList<Date>=
				
//		String s = x.toString();
//		String e = y.toString();
//		LocalDate start = LocalDate.parse(s);
//		LocalDate end = LocalDate.parse(e);
//		List<LocalDate> totalDates = new ArrayList<>();
//		while (!start.isAfter(end)) {
//		    totalDates.add(start);
//		    start = start.plusDays(1);
//		}
//		 return collection.count(eq(dateOfIssue,totalDates));
		return collection.count(and(gte(dateOfReturn,startDate),lt(dateOfReturn,endDate)));
	}

	@Override
	public void deleteFromTo(Date x, Date y) {
		// TODO Auto-generated method stub
		String s = x.toString();
		String e = y.toString();
		LocalDate start = LocalDate.parse(s);
		LocalDate end = LocalDate.parse(e);
		List<LocalDate> totalDates = new ArrayList<>();
		while (!start.isAfter(end)) {
		    totalDates.add(start);
		    start = start.plusDays(1);
		}
		 collection.deleteMany(eq(dateOfIssue,totalDates));
	}

	@Override
	public void deleteByPersonID(String personID) {
		// TODO Auto-generated method stub
		collection.deleteOne(eq(personID,personID));
		
	}

	@Override
	public Document findByTransactionID(String _transactionID) {
		// TODO Auto-generated method stub
		return collection.find(eq(transactionID,_transactionID)).first();
	}

	@Override
	public MongoCursor<Document> findByPersonID(String _personID) {
		// TODO Auto-generated method stub
		return collection.find(eq(personID,_personID)).iterator();
	}

	@Override
	public MongoCursor<Document> findByPersonName(String _personName) {
		// TODO Auto-generated method stub
		return collection.find(regex(personName, Pattern.compile(_personName, Pattern.CASE_INSENSITIVE))).projection(projectionBasicProperties).iterator();
	}

	@Override
	public MongoCursor<Document> findByIssuetype(String _issuetype) {
		// TODO Auto-generated method stub
		return collection.find(eq(issuetype,_issuetype)).iterator();
	}

	@Override
	public MongoCursor<Document> findByDateOfIssue(Date startDate,Date endDate) {
		// TODO Auto-generated method stub
//		LocalDate date = LocalDate.now();
//		collection.aggregate(Arrays.asList(Aggregates.match(eq($))))
		return collection.find(and(gte(dateOfIssue,startDate),lt(dateOfIssue,endDate))).iterator();
	}

	
	
	@Override
	public MongoCursor<Document> findByDateOfReturn(Date startDate,Date endDate) {
		// TODO Auto-generated method stub
		return collection.find(and(gte(dateOfReturn,startDate),lt(dateOfReturn,endDate))).iterator();
	}

	
	
	@Override
	public MongoCursor<Document> findByBookID(String _bookID) {
		// TODO Auto-generated method stub
		return collection.find(eq(bookID,_bookID)).iterator();
	}

	@Override
	public MongoCursor<Document> findByFineCollected(long _fineCollected) {
		// TODO Auto-generated method stub
		return collection.find(eq(fineCollected,_fineCollected)).iterator();
	}


	@Override
	public MongoCursor<Document> findByCourseyear(String _courseyear) {
		// TODO Auto-generated method stub
		return collection.find(eq(courseyear,_courseyear)).iterator();
	}

	@Override
	public MongoCursor<Document> findByBranch(String _branch) {
		// TODO Auto-generated method stub
		return collection.find(eq(branch,_branch)).iterator();
	}

	@Override
	public MongoCursor<Document> findByDegree(String _degree) {
		// TODO Auto-generated method stub
		return collection.find(eq(degree,_degree)).iterator();
	}

	@Override
	public MongoCursor<Document> findByrRenewIndex(int _renewIndex) {
		// TODO Auto-generated method stub
		return collection.find(eq(renewIndex,_renewIndex)).iterator();
	}

	@Override
	public MongoCursor<Document> findByBookName(String _bookName) {
		// TODO Auto-generated method stub
		return collection.find(eq(bookName,_bookName)).iterator();
	}

	@Override
	public MongoCursor<Document> findByAuthor(String _author) {
		// TODO Auto-generated method stub
		return collection.find(eq(author,_author)).iterator();
	}

	@Override
	public MongoCursor<Document> findCompound(String _issuetype, Date _dateOfIssue, Date _dateOfReturn, String _bookID,
			Integer _fineCollected,String _personID, String _courseyear, String _branch, String _degree,String _bookName, String _author) {
		// TODO Auto-generated method stub
		
		
		
		ArrayList <Bson> filters= new ArrayList<Bson>();

		
		
		if (_issuetype!=null) {

			Bson c=(Bson) eq(issuetype,_issuetype);
			filters.add(c);

		}

		
		if (_bookID!=null) {
			Bson c=(Bson) eq(bookID,_bookID);
			filters.add(c);
		}	
		
		if (_personID!=null) {
			Bson c=(Bson) eq(personID,_personID);
			filters.add(c);
		}
		
		if (_degree!=null) {
			Bson c=(Bson) eq(degree,_degree);
			filters.add(c);
		}
		
		if (_courseyear!=null) {
			Bson c=(Bson) eq(courseyear,_courseyear);
			filters.add(c);
		}
		
		if (_bookName!=null) {
			Bson c=(Bson) eq(bookName,_bookName);
			filters.add(c);
		}
		if (_author!=null) {
			Bson c=(Bson) eq(author,_author);
			filters.add(c);
		}
		
		if (_dateOfIssue!=null) {
			Bson c=(Bson) and(gte(dateOfIssue,dayStartTime(_dateOfIssue)),lt(dateOfIssue,dayEndTime(_dateOfIssue)));
			filters.add(c);
		}
		
		if (_dateOfReturn!=null) {
			Bson c=(Bson) and(gte(dateOfReturn,dayStartTime(_dateOfReturn)),lt(dateOfReturn,dayEndTime(_dateOfReturn)));
			filters.add(c);
		}
		
		System.out.println(filters.toString());		
		return  collection.find(and(filters)).iterator();
				
	
	}
	
	@Override
	public MongoCursor<Document> MostBooksUsed() {
		AggregateIterable<Document> iterable = collection.aggregate(Arrays.asList(
				Aggregates.group("$bookTitleID", Accumulators.sum("count", 1))));
		
		return (MongoCursor<Document>) iterable;
	}

	@Override
	public MongoCursor<Document> MostUsedByPerson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertOne(Document TransactionHistory) {
		// TODO Auto-generated method stub
		collection.insertOne(TransactionHistory);
	}

	@Override
	public AggregateIterable<Document> findByBookTitleID(String bookTitleID) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
