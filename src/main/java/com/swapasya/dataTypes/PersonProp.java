package com.swapasya.dataTypes;

public class PersonProp {
	
	// Person properties has to be very flexible
	
	//public static final String personID = "personID"; // Library ID of person e.g. BE10403 , 10403 etc.
	public static final String personID = "_id"; // Library ID of person e.g. BE10403 , 10403 etc.
	public static final String password = "password"; // password for student account 
	public static final String personName = "personName"; // full Name of person
	public static final String currentAddress = "currentAddress"; // address of student
	public static final String permanentAddress = "permanentAddress"; // address of student
//	public static final String gUser = "gUser";
	public static final String emailId = "emailId";
	public static final String mobileNo = "mobileNo"; // For notification
//	public static final String contactNo = "contactNo";  (optional)
	public static final String degree = "degree"; // e.g. BE , ME , MBBS, BA etc.   plus branch
	public static final String branch = "branch"; // e.g. mechanical,  Hindi etc.
	public static final String courseyear = "courseyear"; // e.g. year in course e.g. 3rd year, first year
	public static final String division = "division";
	public static final String rollNo = "rollNo"; // e.g. 23 , B45 , A47
	
	public static final String parentName = "parentName";
	public static final String parentContact = "parentContact";
	
	public static final String role = "role"; // (e.g student, teacher,principle,HOD) (Act as Designation )
	//public static final String accessType = "accessType"; // (e.g studentAccess, teacherAccess,principle,HOD)
	public static final String readerType = "readerType"; // (e.g. studentReder, teacherReader, topper, gate etc.
	public static final String admissionDate = "admissionDate";
//	public static final String transactionID = "transactionID"; // (transactionIDs)
	
	
	// Following fields are in disscusion
	
//	public static final String unRegisteredPersonID = "unRegisteredPersonID";
	
//	public static final String inPossession = "inPossession"; // IDs of book in possession seperated by ; e.g. ME406;MECH233;KK6677
//	public static final String totalCategoryBksInPossesion = "totalCategoryBksInPossesion";
//	public static final String isBookBankEnabled = "isBookBankEnabled";
	//public static final String bookBankID = "bookBankID";   // row number of bookBankBook table
	//alowed issue types .... bookkbank , 

}
