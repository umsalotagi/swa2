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


public class BookTitleReadTest {
	
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
			
			
			Document b3 = new Document("_id", "19A.1").append(BookProp.categoryType, "book");
			Document b4 = new Document("_id", "19A.2").append(BookProp.categoryType, "cd");
			Document bt2 = new Document("_id", "19A").append(bookName, "two name2").append(bookTitleID, "19A").append(author, "two authour1")
					.append(publication, "two publication1").append(noOfPages, 230).append(books, Arrays.asList(b3, b4)).append(tags, Arrays.asList(".NET", "english","computer"));
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
	public void findByBookTitle() {
		assertEquals("Asserting bookName ", "18A",btr.findByBookName("one name1").next().getString("_id"));
		
		MongoCursor<Document> cur = btr.findByBookName("name");
		int x=0;
		while (cur.hasNext()) {
			Document d = cur.next();
			
			System.out.println(d.getInteger(noOfPages) + " no of pages");
			
			assertTrue(d.getInteger(noOfPages)==null);
			
			x++;
		}
		
		assertEquals(4, x);
		
	}
	
	@Test
	public void findByTag() {
		assertEquals( "18A",btr.findByTag("java").next().getString("_id"));
		
		// COMPUTER does not work only computer works
		MongoCursor<Document> cur = btr.findByTag("computer");
		int x=0;
		while (cur.hasNext()) {
			Document d = cur.next();
			
			System.out.println(d.getInteger(noOfPages) + " no of pages");
			System.out.println(d.getString(bookName) + " author pages");
			
			assertTrue(d.getInteger(noOfPages)==null);
			
			x++;
		}
		
		assertEquals(2, x);
		
	}
	
	@Test
	public void findByBookId() {
		assertEquals( "18A",btr.findByBookId("18A.1").getString("_id"));
	}
	
	@Test
	public void findByBookTitleId() {
		assertEquals( "two authour1",btr.findByBookTitleId("19A").getString(author));
	}
	
	@Test
	public void addBookToBookTitle() {
		
	//	btr.addBookToBookTitle("18A", DocumemtRepository.book("18A.4", null, 440, "cd"));
		
	//	assertEquals("18A", btr.findByBookId("18A.4").getString(bookTitleID));
	}
	
	@Test
	public void removeBookFromBookTitle() {
		btr.removeBookFromBookTitle("18A", "18A.4");
		
		assertTrue(btr.findByBookId("18A.4")==null);
		
		btr.removeBookFromBookTitle("18A", "18A.7");
		
		assertTrue(btr.findByBookId("18A.7")==null);
	}
	
	@Test
	public void addToWaitList () {
		
		btr.addInWaitList("p01", "18A.1");
		
		assertEquals("18A", btr.findInWaitList("p01", "18A.1"));
		
		btr.addInWaitList("p02", "18A.1");
		
		assertEquals("18A", btr.findInWaitList("p02", "18A.1"));
		
		btr.removeFromWaitList("p01", "18A.1");
		
		assertEquals(null, btr.findInWaitList("p01", "18A.1"));
		
		btr.removeFromWaitList("p01", "18A.1");
		
		assertEquals(null, btr.findInWaitList("p01", "18A.1"));
		
	}
	
	@Test
	public void issueBookToPerson () {
		
		btr.issueBookToPerson("18A.1", "p02", "NormalIssue", new Date(), null);
		
		assertEquals(btr.findByBookId("18A.1").getString(bookTitleID) + " ID success", "18A", btr.findByBookId("18A.1").getString(bookTitleID) );
		
		System.out.println(btr.findByBookId("18A.1").getString(bookTitleID) + " ID success3");
		assertEquals("p02", btr.personWhomeBookIssued("18A.1"));
		
		//btr.findByBookId("18A.1").getString(key)
		
	}
	
	@Test
	public void issueBookToPersonSCID () {
		
//		btr.issueBookToPerson("18A.2", "p02", "NormalIssue", new Date(), null);
//		assertEquals(false, btr.isBookAvailable("18A.2"));
	}
	
	
	@Test
	public void issueBookToPersonSCID2 () {
		
	//	btr.tempDeleteAddToAssignList("p01", "18A.2");
		
		
		
		btr.issueBookToPersonACID("18A.2", "p01", "NormalIssue", new Date(), null);
		System.out.println(btr.personWhomeBookIssued("18A.2") + " strrrr");
		System.out.println(btr.personWhomeBookIssued("18A.1") + " strrrr");
		System.out.println(btr.personWhomeBookIssued("19A.2") + " strrrr");
		
		assertEquals("p01", btr.personWhomeBookIssued("18A.2"));
		
	}
	
	@Test
	public void countBooksInTitle () {
		assertEquals(2, btr.countBooksInGivenTitle("18A"));
	}

}
