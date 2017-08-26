package com.swapasya.core;

import java.util.*;
import java.io.*;

import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.swapasya.dataTypes.BookTitleProp;
import com.swapasya.domains.*;
import com.swapasya.model.DBConnect;

public class MyClass {

	public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException {
		
		MyClass m = new MyClass();
		MongoOperations op=DBConnect.getConnection();
		BookTitleRepositoryMongoDB mdb=new BookTitleRepositoryMongoDB(op);
		// issue with finding string contains ( symbol
		System.out
				.println(mdb.findByBookTitle("The Immortals of Meluha ").get(0).getBookTitleID());
		System.out.println(mdb.findOne("T_BT0051").getAuthor());
		m.addBookFromCSV ("");
	//	m.oldStyle();
	//	LibraryController lc = new LibraryController ();
	//	lc.addBookFromCSV("");

	}
	
	public void queryDoc () {
		MongoOperations op=DBConnect.getConnection();
		BookTitleRepositoryMongoDB mdb=new BookTitleRepositoryMongoDB(op);
		ArrayList <BookTitle> btl = (ArrayList<BookTitle>) mdb.findByBookTitle("Yayati");
		
		for (BookTitle bt : btl) {
			System.out.println(bt.getBookTitleID());
			System.out.println(bt.getBooks().get(0).getBookID());
			System.out.println(bt.getBookName());
		}
		
		System.out.println("found by authour $$$$$$$$$$ ");
		ArrayList <BookTitle> btl3 = (ArrayList<BookTitle>) mdb.findByAuthor(" V.S. Khandekar ");
		
		for (BookTitle bt : btl3) {
			System.out.println(bt.getBookTitleID());
			System.out.println(bt.getBooks().get(0).getBookID());
			System.out.println(bt.getBookName());
		}
		System.out.println("found by both ");
		
		
		List<BookTitle> btl5 = mdb.findByBookTitleAndAuthour("Yayati"	, " V.S. Khande");
		
		for (BookTitle bt : btl5) {
			System.out.println(bt.getBookTitleID());
			System.out.println(bt.getBooks().get(0).getBookID());
			System.out.println(bt.getBookName());
		}
		
		System.out.println("found by both 666");
		
		DBCursor btl2 = mdb.findByBookTitleAndAuthour2("Yayati"	, " V.S. Khandekar ");
		
		System.out.println(btl2.size());
		
		for (DBObject bt : btl2) {
			System.out.println("in the list");
			BasicDBList bdbl = (BasicDBList) bt.get("BookTitle");
			for (int i=0; i<bdbl.size();i++) {
				BasicDBObject bt2 = (BasicDBObject) bdbl.get(i);
				System.out.println(bt2.getString(BookTitleProp.bookTitleID));;
				System.out.println(bt2.getString(BookTitleProp.authour));;
			}
			
		}
	}
	
	public void oldStyle () throws NumberFormatException, IOException {
		MongoOperations op=DBConnect.getConnection();
		BookTitleRepositoryMongoDB mdb=new BookTitleRepositoryMongoDB(op);

		MyClass myClass=new MyClass();
		File file=myClass.getFile("BookCatalogue.csv");


		BufferedReader bir=new BufferedReader(new FileReader(file));
		String s=null;
		ArrayList<String> getLine=new ArrayList<>();
		int noOfPages=0;
		int i=0;
		while((s=bir.readLine())!=null)
		{
			System.out.println("Inside while");
			getLine.add(s);
			String line=getLine.get(i);
			String splitted[]=line.split(",");
			BookTitle bookTitle;
		
			String tagsFromFile[]=splitted[6].split(":");
			ArrayList<String> tags=new ArrayList<>();
			
			for(String tag:tagsFromFile)
			{
				tags.add(tag);
			}
			if(splitted[7].equals("") || splitted[7]==null){
				noOfPages=0;
			}
			else
				noOfPages=Integer.parseInt(splitted[7]);
			
				bookTitle=new BookTitle(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4], splitted[5], tags,noOfPages , splitted[8], splitted[9], splitted[10]);
				mdb.insertOne(bookTitle);
				System.out.println("Inserted Successfully");
				i++;
		}
	}
	
	File getFile(String fileName){

		ClassLoader classLoader = getClass().getClassLoader();
		System.out.println(classLoader);
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
	
	
	
	public void addBookFromCSV(String pathToFile) throws NumberFormatException, IOException {


		// line of code to read file and store in arrayList
		File file = getFile("BookCatalogue.csv");

		BufferedReader bir = new BufferedReader(new FileReader(file));
		String rowStr = null;

		while ((rowStr = bir.readLine()) != null) {
			//System.out.println(rowStr);

			String row[] = rowStr.split(",");

			String bookID = row[0];
			if (bookID.equals("BookID")) {
				continue;
			}

			String isbnNumber = row[1];
			String bookName = row[2];
			String authour = row[3];
			String publication = row[4];
			String bindingType = row[5];
			String priceStr = row[6];
			String noOfPagesStr = row[7];
			String language = row[8];
			String categoryType = row[9];
			//System.out.println(isbnNumber + " " +categoryType + " :" + row [10]);
			
			Date purchaseDate = null ; // = row [11];
			String[] tagsL = null;
			ArrayList <String> tags = new ArrayList <String>();
			try {
				tagsL = row[11].split(";");
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
					bindingType, tags, noOfPages, language);

		}


	}
	

	public void addBookCSV(String bookID, String isbnNumber, String bookName, String authour, String categoryType,
			String publication, float price, Date purchaseDate, String bindingType, ArrayList<String> tags,
			int noOfPages, String language) {

		BookTitleRepositoryMongoDB bktitlerepo = new BookTitleRepositoryMongoDB(DBConnect.getConnection());

		// query.addCriteria(Criteria.where("publication").regex(Pattern.compile(publication,
		// Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		// Aggregation
		// AggregateOperation<T>
		// if both are sma ethen

		List<BookTitle> btl = bktitlerepo.findByBookTitleAndAuthour(bookName, authour);

		if (btl.isEmpty()) {
			System.out.println("This is new book ###########" + bookName);
			String bookTitleID = "T_" + bookID;
			// location not given ; keeping it null and also imgPath as ""
			BookTitle bt = new BookTitle(bookTitleID, isbnNumber, bookName, authour, publication, bindingType, tags,
					noOfPages, language, null, "");
			Book b = new Book(bookID, purchaseDate, price, categoryType);
			bt.addBook(b);
			bktitlerepo.save(bt);
			System.out.println("Saved new book : "+ bookTitleID + " "+ bookName + " " + authour);
		} else if (btl.size() == 1) {
			System.out.println("This is Duplicate Book $$$$$$$");
			Book b = new Book(bookID, purchaseDate, price, categoryType);
			addBookInBookTitle(bktitlerepo, btl.get(0), b);
			System.out.println("Duplicate " + bookName + " " + authour);
		} else {
			System.out.println("Findinf more %%%%%%%%");
			for (BookTitle bt : btl) {
				if (bt.getPublication().equalsIgnoreCase(publication)) {
					Book b = new Book(bookID, purchaseDate, price, categoryType);
					addBookInBookTitle(bktitlerepo, bt, b);
					System.out.println("Duplicate 333" + bookName + " " + authour);
					break;
				}
			}
		}

	}

	private void addBookInBookTitle(BookTitleRepositoryMongoDB bktitlerepo, BookTitle bookTitle, Book b) {
		bookTitle.addBook(b);
		System.out.println("count " + bookTitle.getBooks().size() + " " + bookTitle.getBookTitleID());
		bktitlerepo.save(bookTitle);

	}

	public void addMeToWaitORAssignList(String personID, String bookTitleID) throws TransactionFailed {

		BookTitleRepositoryMongoDB bktitlerepo = new BookTitleRepositoryMongoDB(DBConnect.getConnection());

		BookTitle bt = bktitlerepo.findOne(bookTitleID);
		bt.getAssignList();

	}
			

}
