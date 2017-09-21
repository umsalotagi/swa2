package com.swapasya.repo;

import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

public interface CRCalenderReadIn {
	
	// eventStartDate and end date will be Fixed Time table validity
	void createCalenderFromFixedTimeTable(Document repeatativeEvent, Date eventStartDate, Date eventEndDate);
	void createCalenderFromFixedTimeTable(String timeTableName);
	
	MongoCursor<Document> showTodaysCalenderFor (String personID);
	MongoCursor<Document> showWeekCalenderFor (String personID);
	
	void presentyFor (String eventID, List<String> personID);
	
	void addHoliday (Date date);
	
	void createRegularCalenderFor (Date date);
	
	void deleteEvent (String eventID);
	void deleteEvent (List<String> eventID);
	
	void addEvent (String teacherID, String subject, String classRoomID,Date date, Date from, Date to);


}
