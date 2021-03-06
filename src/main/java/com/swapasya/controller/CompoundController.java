package com.swapasya.controller;

import static com.swapasya.dataTypes.BookTitleProp.bookTitleID;
import static com.swapasya.dataTypes.BookTitleProp.books;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.swapasya.dataTypes.BookTitleProp;
import com.swapasya.dataTypes.PersonProp;
import com.swapasya.dataTypes.RulesProp;
import com.swapasya.repo.BookTitleRead;
import com.swapasya.repo.DocumemtRepository;
import com.swapasya.repo.LibraryRulesRead;
import com.swapasya.repo.PersonRead;
import com.swapasya.repo.TransactionHistoryRead;

@RestController

public class CompoundController {
	
	@RequestMapping(value="/uploadFile/{fileName}",method=RequestMethod.GET)
	public void uploadFile(@PathVariable String fileName)
	{
		
	}
	
	
	@RequestMapping(value="/readFile/{pathToFile}",method=RequestMethod.GET)
	public void readCSVFile (@PathVariable String pathToFile){
		File file = getFile("BookCatalogue.csv");

		try{
		BufferedReader bir = new BufferedReader(new FileReader(file));
		String rowStr = null;

		while ((rowStr = bir.readLine()) != null) {
			//System.out.println(rowStr);

			String row[] = rowStr.split(",");

			String bookID = row[0].trim();
			if (bookID.equals("BookID")) {
				continue;
			}

			String isbnNumber = row[1].trim();
			String bookName = row[2].trim();
			String authour = row[3].trim();
			String publication = row[4].trim();
			String bindingType = row[5].trim();
			String priceStr = row[6].trim();
			String noOfPagesStr = row[7].trim();
			String language = row[8].trim();
			String categoryType = row[9].trim();
			//System.out.println(isbnNumber + " " +categoryType + " :" + row [10]);
			
			Date purchaseDate = null ; // = row [11];
			String[] tagsL = null;
			ArrayList <String> tags = new ArrayList <String>();
			try {
				tagsL = row[11].trim().split(";");
				for (String l:tagsL) {
					tags.add(l);
				}
			} catch (Exception e) {
				
			}

			if (priceStr.isEmpty() || priceStr == "") {
				priceStr = "0";
			}

			if (noOfPagesStr.isEmpty() || noOfPagesStr.equals("")) {
				noOfPagesStr = "0";
			}

			float price = Integer.parseInt(priceStr);
			int noOfPages = Integer.parseInt(noOfPagesStr);

			addBookCSV(bookID, isbnNumber, bookName, authour, categoryType, publication, price, purchaseDate,
					bindingType, tags, noOfPages, language, null, null);
		
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private File getFile(String fileName){

		ClassLoader classLoader = getClass().getClassLoader();
		System.out.println(classLoader);
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
	
	public void addBookCSV(String _bookID, String _isbnNumber, String _bookName, String _author, String _categoryType,
			String _publication, float _price, Date _purchaseDate, String _bindingType, ArrayList<String> _tags,
			int _noOfPages, String _language, String _location, String _imgPath) {
		
		BookTitleRead bookTitleRead=new BookTitleRead("test4");
		
		Document bt = bookTitleRead.findByBookNameAndAuthorAndPub(_bookName, _author, _publication);
		
		if (bt == null ) {
			// No dup book found
			String _bookTitleID = "T-"+_bookID; // change this way
			
			Document bt1 = DocumemtRepository.emptyListBookTitle(_bookTitleID, _isbnNumber, _bookName, _author, _publication, _bindingType, 
					_tags, _noOfPages, _language, _location, _imgPath);
			
			Document d = DocumemtRepository.book(_bookID, _purchaseDate, _price, _categoryType);
			
			bt1.append(books, d);
			
			bookTitleRead.insertOne(bt1);
		} else if (bt.getString(bookTitleID)!=null){
			
			Document d = DocumemtRepository.book(_bookID, _purchaseDate, _price, _categoryType);
			
			bookTitleRead.addBookToBookTitle(bt.getString(bookTitleID), d);
			
		}
		
		
	}
	
	
	
	
	public void issueBook (String _bookID, String _personID) {
		issueBook(_bookID, _personID, "NormalIssue");
	}
	
	public void issueBook (String _bookID, String _personID, String _issueType) {
		
		String database = "test12";
		
		PersonRead pr = new PersonRead(database);
		String readerType = null ;
		try {
			// we may need to use reader type instead of reader
			readerType = pr.getReaderType(_personID);
			if (readerType==null) {
				// person hasnot joineded library
				return;
			}
		} catch (Exception e) {
			// invalid person
			return;
		}
		
		
		LibraryRulesRead lr = new LibraryRulesRead(database);
		// _issueType  is Normal then we need category type CD/Book/Magazine
		Document rules = null;
		
		BookTitleRead btr = new BookTitleRead(database);
		
		Document btReadForIssue = btr.readForIssueBook(_bookID);
		
		if (btReadForIssue==null) {
			// invalid bookID
			return;
		}
		
		String bkID = btReadForIssue.getString("_id");
		
		// check in assignlist
		if (btr.findInAssignList(_personID, _bookID)==null) {
			// person is not in assign list
			
			// check whether all available books are assigned are NOT
			if (btr.countAvailableBooksInBookTitle(bkID) <= btr.countAssignListedPersons(bkID)) {
				// all books assigned
				return;
			}
		}
		
		
		if (_issueType.equals("NormalIssue")) {
			rules = lr.findRulesFor(readerType, btReadForIssue.getString(BookTitleProp.books_categoryType));
		} else {
			rules = lr.findRulesFor(readerType, _issueType);
		}
		
		
		if (rules==null) {
			// Rules are not set 
			
			
		}
		
		if (_issueType.equals("NormalIssue")) {
			if ( (int)btr.countBkIssuedToCategoryWiseNormalIssue(_personID, btReadForIssue.getString(BookTitleProp.books_categoryType)) >= rules.getInteger(RulesProp.maxQuantity, 50)) {
				// max qty number reached
				return;
			}
			
		} else {
			if ( (int)btr.countBkIssuedTo(_personID, _issueType) >= rules.getInteger(RulesProp.maxQuantity, 50)) {
				// max qty number reached
				return;
			}
		}
		
		
		if (btReadForIssue.getString(BookTitleProp.books_borrowedBy) !=null) {
			// Book is already issues
			return;
		}
		
		
		Date tempDate = new Date ();
		
		int tempDays = rules.getInteger(RulesProp.dayLimit);
		
		

		// update book Fields
		btr.issueBookToPersonACID(_bookID, _personID, _issueType,  tempDate, Utility.addLibDays(tempDate, tempDays));
		
	}
	
	
	
	public void returnBook (String _bookID) {
		
		String database = "test12";
		
		BookTitleRead btr = new BookTitleRead(database);
		
		String _personID;
		
		// check for dt limit fine.
		Document bt = btr.findByBookIdForReturnAndTransactionHistory(_bookID);
		if (bt==null) {
			// invalid bookID
			return;
		}
		
		_personID = bt.getString(BookTitleProp.books_borrowedBy);
		if (_personID==null) {
			// book is not issued
			return;
		}
		
		Date expectedReturnDate = bt.getDate(BookTitleProp.books_expectedReturnDate);
		
		PersonRead pr = new PersonRead(database);
		Document person = pr.findByPersonId(_personID);
		
		LibraryRulesRead lr = new LibraryRulesRead(database);
		String _bkCatOrIssueType = null;
		
		if (bt.getString(BookTitleProp.books_issuedType).equals("NormalIssue")) {
			_bkCatOrIssueType= bt.getString(BookTitleProp.books_categoryType);
		} else {
			_bkCatOrIssueType= bt.getString(BookTitleProp.books_issuedType);
		}
		
		
		Document rules = lr.findRulesFor(person.getString(PersonProp.readerType), _bkCatOrIssueType);
		int fine= (Utility.daysBetween(bt.getDate(BookTitleProp.books_expectedReturnDate), new Date()) 
				-  rules.getInteger(RulesProp.dayLimit, 300))*	rules.getInteger(RulesProp.finePerDay);
		
		if (fine>0) {
			// prompt for fine and get whether to continue or cancel
		} else {
			fine=0;
		}
		
		btr.returnBookACID(_bookID);
		Document his = DocumemtRepository.TransactionHistory(bt.getString(BookTitleProp.books_issuedType), bt.getDate(BookTitleProp.books_issueDate), 
				bt.getDate(BookTitleProp.books_expectedReturnDate), _bookID, fine, _personID, person.getString(PersonProp.courseyear), 
				person.getString(PersonProp.branch), person.getString(PersonProp.degree), 0, bt.getString(BookTitleProp.bookName), bt.getString(BookTitleProp.author));
		// transaction history
		
		TransactionHistoryRead thr = new TransactionHistoryRead(database);
		thr.insertOne(his);
	}


	
	
	
	
	

}
