package com.swapasya.repo;

import org.bson.Document;

public interface TimeTableFixedReadIn {
	
	void addEvent (Document repeatativeEvent) ;
	void removeEvent (String teacherID, String subject, String crassRoomID);
	
	void addLectureInEvent (String teacherID, String subject, String crassRoomID, Document lecturePactical);
	void removeLectureInEvent (String teacherID, String subject, String crassRoomID, Document lecturePactical);

}
