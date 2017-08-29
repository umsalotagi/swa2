package com.swapasya.dataTypes;

public class LibraryAdminProp {
	
	// Library Admin Does not have tenant
	// stored in same LibraryRules collection
	// we are repeating some of properties as CollegeProp is NOT in this database
	
	public static final String collegeID = "collegeID";
	public static final String collageName = "collageName";
	public static final String collegeAddress = "collegeAddress";
	
	public static final String libraryAdmin = "libraryAdmin";
	public static final String libraryAdminName = "libraryAdminName";
	
	public static final String principle = "principle";
	public static final String principleName = "principleName";
	
	
	public static final String availableReaderTypes = "availableReaderTypes";
	
	
	public static final String availableIssueTypes = "availableIssueTypes";
	public static final String availableCategoryTypes = "availableCategoryTypes";

	
	
	// stored in same LibraryRules collection
	// PlatformAdminProp
	public static final String availableRoles = "availableRoles";
	public static final String availableAccessTypes = "availableAccessTypes";
	

}
