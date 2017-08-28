package com.swapasya.controller;

import java.util.Date;

import org.bson.Document;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.client.MongoCursor;
import com.swapasya.repo.BookTitleRead;
import com.swapasya.repo.TransactionHistoryRead;


@RestController
@RequestMapping("/Transaction")
public class TransactionHistoryController {
	
	//Querying in TransactionHistory
		@RequestMapping(value="/multiQueryHistory/{issuetype,dateOfIssue,dateOfReturn, bookID,fineCollected,personID, courseyear, branch, degree,bookName, author}",method=RequestMethod.GET)
		public MongoCursor<Document> findCompound(@PathVariable String issuetype, Date dateOfIssue, Date dateOfReturn, String bookID,
				Integer fineCollected,String personID, String courseyear, String branch, String degree,String bookName, String author) {
			
			MongoCursor<Document> multiQueryHistoryDoc=null;
			TransactionHistoryRead TransactionHistoryRead =new TransactionHistoryRead("test4");
		
		   multiQueryHistoryDoc = TransactionHistoryRead.findCompound(issuetype,dateOfIssue,dateOfReturn, bookID,fineCollected,personID, courseyear, branch, degree,bookName, author);
		   
		   return multiQueryHistoryDoc;
		}
	

}
