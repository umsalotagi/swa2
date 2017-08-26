package com.swapasya.repo;

import static com.swapasya.dataTypes.BookTitleProp.*;

import  com.swapasya.dataTypes.TransactionHistoryProp;

import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

import com.swapasya.dataTypes.BookProp;

public class DocumemtRepository {
	
	public static Document emptyListBookTitle (String _bookTitleID, String _isbnNumber, String _bookName, String _author, String _publication,
			String _bindingType, ArrayList<String> _tags, int _noOfPages, String _language,String _location,String _imgPath) {
		
		return new Document(bookTitleID, _bookTitleID).append(isbnNumber, _isbnNumber).append(bookName, _bookName).append(author, _author)
				.append(publication, _publication).append(bindingType, _bindingType).append(tags, _tags).append(noOfPages, _noOfPages)
				.append(language, _language).append(location, _location).append(imgPath, _imgPath);
		
	}
	
	public static Document book (String _bookID, Date _purchaseDate, double _price, String _categoryType) {
		
		return new Document(BookProp.bookID, _bookID).append(BookProp.purchaseDate, _purchaseDate)
				.append(BookProp.price, _price).append(BookProp.categoryType, _categoryType);
		
	}
	
	public static Document waitList (String _personID, long _timestamp) {
		
		return new Document(s_personID, _personID).append(s_timestamp, _timestamp); 
	}
	
	public static Document assignList (String _personID, long _timestamp) {
		
		return new Document(s_personID, _personID).append(s_timestamp, _timestamp); 
	}
	
	
//	TransactionHistory Document

	public static Document TransactionHistory (String _issuetype, Date _dateOfIssue, Date _dateOfReturn, String _bookID,
			long _fineCollected,String _personID, String _courseyear, String _branch,String _degree,int _renewIndex,String _bookName,String _author) {
		
		return new Document(TransactionHistoryProp.issuetype, _issuetype).append(TransactionHistoryProp.dateOfIssue, _dateOfIssue).append(TransactionHistoryProp.dateOfReturn, _dateOfReturn).append(TransactionHistoryProp.bookID, _bookID)
				.append(TransactionHistoryProp.fineCollected, _fineCollected).append(TransactionHistoryProp.personID, _personID).append(TransactionHistoryProp.courseyear, _courseyear).append(TransactionHistoryProp.branch, _branch)
				.append(TransactionHistoryProp.degree, _degree).append(TransactionHistoryProp.renewIndex, _renewIndex).append(TransactionHistoryProp.bookName, _bookName).append(TransactionHistoryProp.author, _author);
		
	}
	
	
}
