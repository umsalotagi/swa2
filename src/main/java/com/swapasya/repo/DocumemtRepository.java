package com.swapasya.repo;

import static com.swapasya.dataTypes.BookTitleProp.*;

import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

import com.swapasya.dataTypes.BookProp;

public class DocumemtRepository {
	
	public static Document emptyListBookTitle (String _bookTitleID, String _isbnNumber, String _bookName, String _authour, String _publication,
			String _bindingType, ArrayList<String> _tags, int _noOfPages, String _language,String _location,String _imgPath) {
		
		return new Document(bookTitleID, _bookTitleID).append(isbnNumber, _isbnNumber).append(bookName, _bookName).append(author, _authour)
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
	
	
	
}
