package com.swapasya.repo;

import static org.junit.Assert.*;

import java.awt.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.client.MongoCursor;

import static com.swapasya.dataTypes.TransactionHistoryProp.*;
import static com.swapasya.dataTypes.NameKinds.*;

public class TransactionHistoryTest {
	
	static TransactionHistoryRead thr;
	
	static Date date= new Date();

	
	public static  Date provideDate(int year, int monthfrom0, int date){
	      Calendar cal = Calendar.getInstance();
	      cal.set(year,monthfrom0,date,0,0,1);
	      
	      return cal.getTime();
		}	
	
	public static  Date forToday(){
	      Calendar cal = Calendar.getInstance();
	      int year=cal.get(cal.YEAR);
	      int month=cal.get(cal.MONTH);
	      int date=cal.get(cal.DATE);
	      cal.set(year, month, date,0,0);
	      System.out.println("SET time to: "+cal.getTime());
	      return cal.getTime();
		}
	
	public static  Date addDays(Date date){
	      Calendar cal = Calendar.getInstance();
	      cal.setTime(date);
	      cal.add(Calendar.DATE, 7);
	  //    cal.set
	      return cal.getTime();
		}
	public static  Date addDays2(int days){
	      Calendar cal = Calendar.getInstance();
	      Date date= new Date();
	      cal.setTime(date);
	      cal.add(Calendar.DATE, days);
	      return cal.getTime();
	      
		}
	
	Date date1= provideDate(2017,7,27);
	Date date2= provideDate(2017,7,28);
	Date date3= provideDate(2017,8,16);
	Date date4= provideDate(2017,8,17);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("start test");
		// We will add data in here
		thr = new TransactionHistoryRead("test3");

		

				
		try {
			thr.insertOne(new Document(issuetype,"Normal").append(dateOfIssue, date).append(dateOfReturn,addDays(date)).append(bookID,"40A.3").append(bookTitleID,"40A").append(fineCollected, 40).append(personID, "1020")
					.append(courseyear, "Third").append(branch, "Mechanical").append(degree, "BE").append(bookName, "bookname1").append(author, "author1"));

			thr.insertOne(new Document(issuetype,"Normal").append(dateOfIssue, date).append(dateOfReturn,addDays(date)).append(bookID,"40A.2").append(bookTitleID,"40A").append(fineCollected, 0).append(personID, "1111")
					.append(courseyear, "Third").append(branch, "Mechanical").append(degree, "BE").append(bookName, "bookname1").append(author, "author1"));
			
			thr.insertOne(new Document(issuetype,"Bookbank").append(dateOfIssue, addDays(addDays(date))).append(dateOfReturn,addDays2(20)).append(bookID,"30A.3").append(bookTitleID,"30A").append(fineCollected, 15).append(personID, "2222")
					.append(courseyear, "Second").append(branch, "Computer").append(degree, "BE").append(bookName, "bookname3").append(author, "author3"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	@Test
//	public void count() {
//		assertEquals("Asserting count: ", 3,thr.count());
//	}
//	
//	@Test
//	public void countToday() {
//		MongoCursor<Document> cur = thr.findByDateOfReturn(date3,date4);
//		int x=0;
////		System.out.println(date3 +" | " + date4 );
//		System.out.println("Todays date: "+ forToday());
//
//		while (cur.hasNext()) {
//			Document d = cur.next();
//			System.out.println(d.getDate(dateOfReturn).toString()+" "+d.getString("bookID"));	
//			x++;
//		}
//		
//		assertEquals(1, x);
//	}
//		
//	@Test
//	public void findByIssuetype() {
////		assertEquals("Asserting Issuetype ", "Normal",thr.findByIssuetype("Normal"));
//		
//		MongoCursor<Document> cur = thr.findByIssuetype("Normal");
//		int x=0;
//		while (cur.hasNext()) {
//			Document d = cur.next();
//			
//			System.out.println(d.getString("bookID"));	
//			x++;
//		}
//		
//		assertEquals(2, x);
//		
//	}	
//	
//	@Test
//	public void findByDateOfIssue() {
//  	
//		MongoCursor<Document> cur = thr.findByDateOfIssue(date1,date2);
//		int x=0;
//		System.out.println(date1 +" | " + date2);
//
//		while (cur.hasNext()) {
//			Document d = cur.next();
//			System.out.println(d.getDate(dateOfIssue).toString()+" "+d.getString("bookID"));	
//			x++;
//		}
//		
//		assertEquals(2, x);
//		
//	}
//	
//	
//	@Test
//	public void findByDateOfReturn() {
//		
//	//	System.out.println(addDays2(20) + " actual date stored");
//		
//		MongoCursor<Document> cur = thr.findByDateOfReturn(date3,date4);
//		int x=0;
////		System.out.println(date3 +" | " + date4 );
//
//		while (cur.hasNext()) {
//			Document d = cur.next();
//			System.out.println(d.getDate(dateOfReturn).toString()+" "+d.getString("bookID"));	
//			x++;
//		}
//		
//		assertEquals(1, x);
//		
//	}
//	
//	@Test
//	public void countReturnedFromTo() {
//		
//		Date date1=  provideDate(2017,7,27);
//		Date date2=  provideDate(2017,8,4);
//		System.out.println("counts for retuen in: "+ date1+" ! "+date2);
//		long x= thr.countFromTo(date1, date2);
//		
//		assertEquals(2, x);
//		
//	}
//		
//	@Test
//	public void findCompound() {
// 
//		MongoCursor<Document> cur = thr.findCompound("Normal",null,null,null,null,"1111",null,null,null,null,null);
//		int x=0;
//	while (cur.hasNext()) {
//		Document d = cur.next();
//		System.out.println("that book is "+d.getString("bookID"));	
//		x++;
//	}
//	
//	assertEquals(1, x);
//	
//   }
	
	@Test
	public void mostBooksUsed(){
		MongoCursor<Document> cur = thr.MostBooksUsed();
		assertEquals("40A", cur);
	}

}
