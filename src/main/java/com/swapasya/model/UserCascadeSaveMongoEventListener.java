package com.swapasya.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;


import com.swapasya.domains.Person;
class UserCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object>
{
	@Autowired
	MongoOperations mongoOperations;
	public void onBeforeConvert(final Object source)  {
		if (source instanceof Person && 
		          ((Person) source).getPersonID() != null) {
		            mongoOperations.save(((Person) source).getPersonID());
		        }
	}
	
}