package com.swapasya.domains;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.swapasya.model.CascadeSave;

public class WaitList {
	@DBRef
	@CascadeSave
	private Person person;
	//private String id;

	private Date timestamp;
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	

/*	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	*/
	
}
