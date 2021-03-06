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
		ptr = new PersonRead("test12");
		try {
			ptr.insertOne(new Document("_id", "p02").append(personName, "B")
					.append(password, "b123").append(address, "pune").append(emailId, "b@gmail.com")
					.append(mobileNo, "7719013162").append("degree", "BE").append(branch, "IT")
					.append(courseyear, "4 th year").append(rollNo, "2").append(role, "student")
					.append(admissionDate, new Date(01, 07, 2017)));
			
			Document p = DocumemtRepository.person("p01", "A", "Rahul", "Shete", null, "rs@g.com", "89997667", "ME", "Mech", 2, 12, "student", null, "Shete", "88776533");
			ptr.insertOne(p);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void count() {
		assertEquals("Asserting count ", 2, ptr.count());

	}

	@Test
	public void findByPersonId() {
		assertEquals("B", ptr.findByPersonId("p02").getString(personName));

	}

	@Test
	public void findByPersonName() {
		assertEquals("Rahul Shete", ptr.findByPersonName("Rahul").next().getString(personName));

		MongoCursor<Document> cur = ptr.findByPersonName("A");
		// int x=0;
		while (cur.hasNext()) {
			Document d = cur.next();

			System.out.println(d.getString(personName));
			System.out.println(d.getString(address));

			// assertTrue(d.getInteger(noOfPages)==null);

			// x++;
		}

		// assertEquals(2, x);

	}
}
