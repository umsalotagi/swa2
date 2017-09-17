package com.swapasya.repo;

public interface ClassRoomReadIn {
	
	void createClassRoomFromSyllabus ();
	
	void addFresherInClassRoom (String personID);
	
	void transferStudentFromBatch (String personID, String toBatchID);
	
	void transferStudentFromBranch (String personID , String toBatchID);
	
	void transferStudentNextBatchWithoutResult();

}
