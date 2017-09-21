package com.swapasya.repo;

import org.bson.Document;

public interface TimeTableFixedReadIn {
	
	void addEvent (Document repeatativeEvent) ;
	void removeEvent (String timeTableName, String teacherID, String subject, String crassRoomID);
	void removeEvent (String id);
	
	void addLectureInEvent (String timeTableName, String teacherID, String subject, String classRoomID, Document lecturePactical);
	void removeLectureInEvent (String timeTableName, String teacherID, String subject, String classRoomID, Document lecturePactical);

}
