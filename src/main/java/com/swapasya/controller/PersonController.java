package com.swapasya.controller;

import org.bson.Document;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoCursor;
import com.swapasya.domains.BookTitle;
import com.swapasya.repo.BookTitleRead;
import com.swapasya.repo.PersonRead;

@RestController
@RequestMapping("person")
public class PersonController {

	@RequestMapping(value = "/search_person/{personID}", method = RequestMethod.GET)
	public MongoCursor<Document> searchPerson(@PathVariable String search, @PathVariable String txt) {

		PersonRead personRead = new PersonRead("test4");
		if (search != null && txt != null) {
			switch (search) {
			case "PersonName":
				return personRead.findByPersonId2(txt);
			// break;

			case "PersonID":
				return personRead.findByPersonName(txt);
			// break;
				
			}
		}
		return null;
	}
	
	
	@RequestMapping(value = "/edit/{personID}", method = RequestMethod.GET)
	public Document showPersonFull(@PathVariable String personID) {

		PersonRead personRead = new PersonRead("test4");
		return personRead.findByPersonIdFULL(personID);

	}

	// updating bookTitle with new changes
	@RequestMapping(value = "/update/", method = RequestMethod.POST)
	public String updatePerson(@RequestBody Document person) {

		PersonRead personRead = new PersonRead("test4");
		personRead.updatePerson(person);
		return "success";
	}
	
	
	@RequestMapping(value = "/insert/", method = RequestMethod.POST)
	public String insertPerson(@RequestBody Document person) {

		PersonRead personRead = new PersonRead("test4");
		personRead.insertOne(person);
		return "success";
	}

	@RequestMapping(value = "/delete/{personID}", method = RequestMethod.DELETE)
	public String deletePerson(@PathVariable String personID) {
		PersonRead personRead = new PersonRead("test4");
		personRead.delete(personID);
		return "success";

	}
	
	
	// update photo api
	
	
	
	
}