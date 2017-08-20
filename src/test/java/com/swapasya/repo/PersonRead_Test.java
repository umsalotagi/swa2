package com.swapasya.repo;

import static org.junit.Assert.*;

import java.util.Date;

import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.swapasya.dataTypes.PersonProp.*;

public class PersonRead_Test {		
		static PersonRead ptr;
		
		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
			System.out.println("start test");
			// We will add data in here
			ptr = new PersonRead("test4");
			try {
				ptr.insertOne(new Document("_id", "p002").append(personName, "A").append(personID, "p001").append(password, "a123")
						.append(address, "pune").append(emailId, "a@gmail.com").append(mobileNo, "7719013162").append(contactNo, "7719013162")
						.append("degree", "BE").append(branch, "IT").append(courseyear, "4 th year").append(rollNo, "1").append(role, "student").append(admissionDate, new Date(01, 07, 2017)));
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		@Test
		public void count() {
			assertEquals("Asserting count ", 1,ptr.count());
			
		}

	}


