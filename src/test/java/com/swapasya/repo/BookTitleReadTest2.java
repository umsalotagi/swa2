package com.swapasya.repo;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.Arrays;
import java.util.Date;

import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.client.MongoCursor;
import com.swapasya.dataTypes.BookProp;

import static com.swapasya.dataTypes.BookTitleProp.*;
import static com.swapasya.dataTypes.NameKinds.*;


public class BookTitleReadTest2 {
	
	static BookTitleRead btr;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("start test");
		// We will add data in here
		btr = new BookTitleRead("test12");
		try {
			btr.insertOne(new Document("_id", "20A").append(bookName, "three name3").append(bookTitleID, "20A").append(author, "three authour1")
					.append(publication, "three publication1").append(noOfPages, 235).append(tags, Arrays.asList("fluid", "english","mech")));
			btr.insertOne(new Document("_id", "21A").append(bookName, "name4").append(bookTitleID, "21A").append(author, "authour1").append(publication, "publication1").append(noOfPages, 240));
			
			Document b1 = new Document("_id", "18A.1").append(BookProp.categoryType, "book");
			Document b2 = new Document("_id", "18A.2").append(BookProp.categoryType, "cd");
			Document bt1 = new Document("_id", "18A").append(bookName, "one name1").append(bookTitleID, "18A").append(author, "one authour1")
					.append(publication, "publication1").append(noOfPages, 225).append(books, Arrays.asList(b1, b2)).append(tags, Arrays.asList("java", "english","computer"));
			btr.insertOne(bt1);
			
			
			Document b3 = new Document("_id", "19A.1").append(BookProp.categoryType, "book").append(BookProp.borrowedBy, "p01").append(BookProp.issuedType, "NormalIssue");
			Document b4 = new Document("_id", "19A.2").append(BookProp.categoryType, "cd");
			Document b5 = new Document("_id", "19A.3").append(BookProp.categoryType, "book").append(BookProp.borrowedBy, null).append(BookProp.issuedType, null);
			Document bt2 = new Document("_id", "19A").append(bookName, "two name2").append(bookTitleID, "19A").append(author, "two authour1")
					.append(publication, "two publication1").append(noOfPages, 230).append(books, Arrays.asList(b3, b4,b5)).append(tags, Arrays.asList(".NET", "english","computer"));
			btr.insertOne(bt2);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	
	@Test
	public void count() {
		assertEquals("Asserting count ", 4,btr.count());
		
	}
	
	@Test
	public void countAvailabeBk() {
		assertEquals(2,btr.countAvailableBooksInBookTitle("19A"));
	}
	
	@Test
	public void countBooksInTitle () {
		assertEquals(2, btr.countBooksInGivenTitle("18A"));
	}


}
