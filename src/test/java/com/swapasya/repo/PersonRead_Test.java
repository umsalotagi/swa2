package com.swapasya.repo;

import static org.junit.Assert.*;

import java.util.Date;

import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.client.MongoCursor;

import static com.swapasya.dataTypes.PersonProp.*;

public class PersonRead_Test {		
		static PersonRead ptr;
		
		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
			System.out.println("start test");
			// We will add data in here
			ptr = new PersonRead("test4");
			try {
				ptr.insertOne(new Document("_id", "p003").append(personName, "B").append(personID, "p003").append(password, "b123")
						.append(address, "pune").append(emailId, "b@gmail.com").append(mobileNo, "7719013162").append(contactNo, "7719014162")
						.append("degree", "BE").append(branch, "IT").append(courseyear, "4 th year").append(rollNo, "2").append(role, "student").append(admissionDate, new Date(01, 07, 2017)));
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		@Test
		public void count() {
			assertEquals("Asserting count ", 2,ptr.count());
			
		}
		
		@Test
		public void findByPersonId() {
			assertEquals( "single person",ptr.findByPersonId("p002").getString(personID));
			
		}
		

		@Test
		public void findByPersonName()
		{
			assertEquals("single person",ptr.findByPersonName("A").next().getString(personName));
			
			MongoCursor<Document> cur = ptr.findByPersonName("A");
			//int x=0;
			while (cur.hasNext()) {
				Document d = cur.next();
				
				System.out.println(d.getString(personName));
				System.out.println(d.getString(address));
				
				//assertTrue(d.getInteger(noOfPages)==null);
				
				//x++;
			}
			
			//assertEquals(2, x);
			
			
		}
	}


