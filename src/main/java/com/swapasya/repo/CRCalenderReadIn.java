package com.swapasya.repo;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

public interface CRCalenderReadIn {
	
	void createCalenderFromFixedTimeTable();
	MongoCursor<Document> showTodaysCalenderFor (String personID);
	MongoCursor<Document> showWeekCalenderFor (String personID);
	
	void presentyFor (String eventID, List<String> personID);

}
