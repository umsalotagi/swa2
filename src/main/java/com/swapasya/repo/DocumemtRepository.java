package com.swapasya.repo;

import static com.swapasya.dataTypes.BookTitleProp.*;

import com.swapasya.dataTypes.TransactionHistoryProp;

import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.swapasya.dataTypes.BookProp;
import com.swapasya.dataTypes.NameKinds;
import com.swapasya.dataTypes.PersonProp;
import com.swapasya.dataTypes.RulesProp;

public class DocumemtRepository {

	public static Document emptyListBookTitle(String _bookTitleID, String _isbnNumber, String _bookName, String _author,
			String _publication, String _bindingType, ArrayList<String> _tags, int _noOfPages, String _language,
			String _location, String _imgPath) {

		return new Document(bookTitleID, _bookTitleID).append(isbnNumber, _isbnNumber).append(bookName, _bookName)
				.append(author, _author).append(publication, _publication).append(bindingType, _bindingType)
				.append(tags, _tags).append(noOfPages, _noOfPages).append(language, _language)
				.append(location, _location).append(imgPath, _imgPath);

	}

	public static Document book(String _bookID, Date _purchaseDate, double _price, String _categoryType) {

		return new Document(BookProp.bookID, _bookID).append(BookProp.purchaseDate, _purchaseDate)
				.append(BookProp.price, _price).append(BookProp.categoryType, _categoryType);

	}

	public static Document waitList(String _personID, long _timestamp) {

		return new Document(s_personID, _personID).append(s_timestamp, _timestamp);
	}

	public static Document assignList(String _personID, long _timestamp) {

		return new Document(s_personID, _personID).append(s_timestamp, _timestamp);
	}

	public static Document person (String _personID, String _password, String _firstName,  String _middleName, String _lastName,
			Document _currentAddress, Document _permanentAddress, String _emailId, String _mobileNo, String _degree, String _branch, 
			int _courseyear, int _rollNo, String _division, String _role, Date _admissionDate, String _parentName, String _parentContact,
			String _accessType, String _readerType) {

		// boolean auth = db.authenticate(myUserName, myPassword);
		// System.out.println("Authentication: "+auth);

		Document doc =  new Document(PersonProp.personID, _personID)
				.append(PersonProp.password, _password).append(PersonProp.personName, _firstName  + " " + _middleName + " "+_lastName)
				.append(PersonProp.currentAddress, _currentAddress).append(PersonProp.permanentAddress, _permanentAddress)
				.append(PersonProp.emailId, _emailId)
				.append(PersonProp.mobileNo, _mobileNo).append(PersonProp.degree, _degree)
				.append(PersonProp.parentName, _parentName).append(PersonProp.parentContact, _parentContact)
				.append(PersonProp.courseyear, _courseyear)
				.append(PersonProp.rollNo, _rollNo).append(PersonProp.role, _role)
				.append(PersonProp.admissionDate, _admissionDate).append(PersonProp.accessType, _accessType).append(PersonProp.readerType, _readerType);
		
		if (_division != null) {
			doc.append(PersonProp.division, _division);
		}
		
		if (_branch!=null) {
			doc.append(PersonProp.branch, _branch);
		}
		
		return doc;

	}
	
	
	public static Document libraryRules(String _readerType, String _bkCatORissueType, int bookQuantityLimit, 
			int dayLimitForEachBook , float _finePerDay, boolean _isCategoryRule, int _maxQuantity) {
		
		return new Document(RulesProp.readerType, _readerType)
				.append(RulesProp.bkCatORissueType, _bkCatORissueType)
				.append(RulesProp.finePerDay, _finePerDay)
				.append(RulesProp.isCategoryRule, _isCategoryRule)
				.append(RulesProp.maxQuantity, _maxQuantity)
				.append(RulesProp.dayLimit, (int) dayLimitForEachBook)
				.append(RulesProp.maxQuantity, (int) bookQuantityLimit);

	}

	// TransactionHistory Document

	public static Document TransactionHistory(String _issuetype, Date _dateOfIssue, Date _dateOfReturn, String _bookID,
			long _fineCollected, String _personID, String _courseyear, String _branch, String _degree, int _renewIndex,
			String _bookName, String _author) {

		return new Document(TransactionHistoryProp.issuetype, _issuetype)
				.append(TransactionHistoryProp.dateOfIssue, _dateOfIssue)
				.append(TransactionHistoryProp.dateOfReturn, _dateOfReturn)
				.append(TransactionHistoryProp.bookID, _bookID)
				.append(TransactionHistoryProp.fineCollected, _fineCollected)
				.append(TransactionHistoryProp.personID, _personID)
				.append(TransactionHistoryProp.courseyear, _courseyear)
				.append(TransactionHistoryProp.branch, _branch)
				.append(TransactionHistoryProp.degree, _degree)
				.append(TransactionHistoryProp.renewIndex, _renewIndex)
				.append(TransactionHistoryProp.bookName, _bookName)
				.append(TransactionHistoryProp.author, _author);

	}

}
