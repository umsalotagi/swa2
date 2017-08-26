package com.swapasya.repo;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import java.time.LocalDate;
import java.util.Date;


public interface TransactionHistoryReadIn {
	
	
	// Read Operations
	
		// Crude preq
		long count();

		void deleteAll();
		void delete(String transactionID);

		
//		boolean exists(String personID);
		MongoCursor<Document> findAll();
		
		
		
		
		// user defined
		long countToday(Date x);
		long countFromTo(Date x,Date y);
		void deleteFromTo(Date x,Date y);
		void deleteByPersonID(String personID);
		
		/*MongoCursor<Document> findByPersonId(String personID);*/
		
		Document findByTransactionID(String transactionID);
		
		MongoCursor<Document> findByPersonID(String personID);
		
		MongoCursor<Document> findByPersonName(String personName);
		
		MongoCursor<Document> findByIssuetype(String issuetype);
		
		MongoCursor<Document> findByDateOfIssue(Date startDate, Date endDate);
			
		MongoCursor<Document> findByDateOfReturn(Date startDate, Date endDate);
		
		MongoCursor<Document> findByBookID(String bookID);
		
		MongoCursor<Document> findByFineCollected(long fineCollected);
		
		MongoCursor<Document> findByCourseyear(String courseyear);
		
		MongoCursor<Document> findByBranch(String branch);
		
		MongoCursor<Document> findByDegree(String degree);
		
		MongoCursor<Document> findByrRenewIndex(int renewIndex);
		
		MongoCursor<Document> findByBookName(String bookName);
		
		MongoCursor<Document> findByAuthour(String authour);
		
		MongoCursor<Document> findCompound(String issuetype,Date dateOfIssue, Date dateOfReturn,String bookID,Integer fineCollected,String personID,String courseyear,String branch,String degree,String bookName,String author);
		
		//Document findByPersonName(String personName);
		
		//Document findByBookIdFULL(String bookId);
		//Document findByBookTitleIdFULL(String bookTitleId);
		
		
		
	// Write Operations
		
		// Crude preq
		void insertOne(Document TransactionHistory);
		
		
		// user defined

}
