package com.swapasya.dataTypes;

public class BookTitleProp {
	
	
	// NOTES
		//  waitlist, no. of books of same title.logic is yet to be decided
		//  same book is not allowed to return and again take away in same day by same person.
		
	//	public static final String bookTitleID = "bookTitleID";   //  ***Mand
		public static final String bookTitleID = "_id";   //  ***Mand
		public static final String isbnNumber = "isbnNumber";
		public static final String bookName = "bookName";    //  ***Mand
		public static final String author="author";       //  ***Mand
		public static final String publication="publication";   
//		public static final String purchaseDate = "purchaseDate";
		public static final String bindingType = "bindingType";
		public static final String noOfPages = "noOfPages";
		public static final String language = "language";
		public static final String imgPath = "imgPath";
		
		public static final String location = "location";
		
		public static final String tags="tags"; // e.g.  english;general
		public static final String books = "Books";
		public static final String waitList = "WaitList";
		public static final String assignList = "AssignList";
		
		
		public static final String waitList_personID = "WaitList.personID";
		public static final String waitList_timestamp = "WaitList.timestamp";
		
		public static final String assignList_personID = "AssignList.personID";
		public static final String assignList_timestamp = "AssignList.timestamp";
		

		public static final String s_personID = "personID";
		public static final String s_timestamp = "timestamp";
		
		
		public static final String books_bookID = "Books._id";   //  ***Mand
		
		public static final String books_purchaseDate = "Books.purchaseDate";
		public static final String books_price = "Books.price";

		public static final String books_borrowedBy = "Books.borrowedBy"; //(personid)
		public static final String books_issuedType = "Books.issuedType"; // (e.g bookbank/normalissue?)(to be decided at run time for return date)
		public static final String books_categoryType = "Books.categoryType"; // (e.g CD/book/magazine)decided while storing book   ***Mand
		public static final String books_issueDate = "Books.issueDate";
		public static final String books_expectedReturnDate = "Books.expectedReturnDate";
		
		
		// type ... 
//		public static final String price = "price";
//		
//		public static final String borrowedBy = "borrowedBy"; //(personid)
//		public static final String issuedType = "issuedType"; // (e.g bookbank/normalissue?)(to be decided at run time for return date)
//		public static final String categoryType = "CategoryType"; // (e.g CD/book/magazine)decided while storing book   ***Mand
//		public static final String issueDate = "issueDate";
//		public static final String expectedReturnDate = "expectedReturnDate";
//		
//		public static final String noOfRenewsAllowed = "noOfRenewAllowed"; // extra for book specific renew
		public static final String rating = "rating";
//		public static final String transactionID = "transactionID"; // (transactionIDs)
		
		


}
