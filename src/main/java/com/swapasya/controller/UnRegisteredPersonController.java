package com.swapasya.controller;

import static com.swapasya.dataTypes.UnRegisteredPersonProp.unRegisteredPersonID;

import org.bson.Document;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoCursor;
import com.swapasya.repo.UnRegisteredPerson;

@RestController
@RequestMapping("unRegisteredPerson")
public class UnRegisteredPersonController {
	
	
	@RequestMapping(value = "/search_person/{personID}", method = RequestMethod.GET)
	public MongoCursor<Document> searchPerson(@PathVariable String search, @PathVariable String txt) {

		UnRegisteredPerson personRead = new UnRegisteredPerson("test4");
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
	public Document showPersonFull(@PathVariable String tempPersonID) {

		UnRegisteredPerson personRead = new UnRegisteredPerson("test4");
		return personRead.findByPersonIdFULL(tempPersonID);

	}

	// updating bookTitle with new changes
	@RequestMapping(value = "/update/", method = RequestMethod.POST)
	public String updatePerson(@RequestBody Document person) {

		UnRegisteredPerson personRead = new UnRegisteredPerson("test4");
		personRead.updatePerson(person);
		return "success";
	}
	
	// This can be used for two things.
	// One for librarian create user by giving userID (permanent) and emailID where registration link goes
	// Two : where user completes the registration by assigning temp userID (Branch + Div+ rollNum)
	// and librarian validate user , userID is created automatically and serially by our server
	@RequestMapping(value = "/insert/", method = RequestMethod.POST)
	public String insertPerson(@RequestBody Document person) {

		UnRegisteredPerson personRead = new UnRegisteredPerson("test4");
		personRead.insertOne(person);
		return "success";
	}

	@RequestMapping(value = "/delete/{personID}", method = RequestMethod.DELETE)
	public String deletePerson(@PathVariable String tempPersonID) {
		UnRegisteredPerson personRead = new UnRegisteredPerson("test4");
		personRead.delete(tempPersonID);
		return "success";

	}
	
	@RequestMapping(value = "/validate/{personID}", method = RequestMethod.DELETE)
	public String validatePerson(@PathVariable Document unRegisteredPerson) {
		UnRegisteredPerson personRead = new UnRegisteredPerson("test4");
		
		// check if automatic ID generation is applicable or not
		
		// CASE-1 : When automatic ID generation not applicable
		String id = unRegisteredPerson.getString(unRegisteredPersonID);
		personRead.delete(unRegisteredPerson);
		
		unRegisteredPerson.remove(unRegisteredPersonID);
		unRegisteredPerson.append("_id", id);
		personRead.insertOne(unRegisteredPerson);
		
		// CASE - 2 : when automatic ID generation is applicable
		
		
		return "success";

	}
	

}
