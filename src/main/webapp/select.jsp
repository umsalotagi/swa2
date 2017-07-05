<%@page import="com.swapasya.domains.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.swapasya.domains.BookTitle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.swapasya.repo.*, org.springframework.context.ApplicationContext,org.springframework.context.annotation.AnnotationConfigApplicationContext,org.springframework.data.mongodb.core.MongoOperations,com.swapasya.config.*,com.swapasya.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%--<%
BookTitleRepositoryMongoDB bktitleRepo=new BookTitleRepositoryMongoDB(DBConnect.getConnection());
BookTitle booktTile=bktitleRepo.findOne(request.getParameter("BookTitleId"));

System.out.println("In select.jsp");
out.print("[");

//while(true)
//{
	out.print("{");
	
		//System.out.println("Inside array");
	out.print("\""+"bookTitleID"+"\":\""+booktTile.getBookTitleID()+"\"");
	/*
	System.out.println(""+booktTile.getBookTitleID());
	out.print(",");
	out.print("isbnNumber"+":\'"+booktTile.getIsbnNumber()+"\'");
	System.out.println(""+booktTile.getIsbnNumber());
	out.print(",");
	out.print("bookName"+":\'"+booktTile.getBookName()+"\'");
	out.print(",");
	out.print("authour"+":\'"+booktTile.getAuthor()+"\'");
	out.print(",");
	out.print("publication"+":\'"+booktTile.getAuthor()+"\'");
	out.print(",");
	out.print("bindingType"+":\'"+booktTile.getBindingType()+"\'");
	out.print(",");
	out.print("noOfPages"+":\'"+booktTile.getNoOfPages()+"\'");
	out.print(",");
	out.print("language"+":\'"+booktTile.getLanguage()+"\'");
	out.print(",");
	out.print("imgPath"+":\'"+booktTile.getImgPath()+"\'");
	out.print(",");
	ArrayList<String> tags=booktTile.getTags();
	out.print("[");
	out.print("{");
	
	for(int i=0;i<tags.size();i++){
	out.print("tags"+":\'");
	out.print(tags.get(i)+"\'");
	if(i<tags.size())
	{
		out.print(",");
	}
		
	}
	out.print("}");
	out.print("]");
	*/
	out.print("}");
	out.print("]");
	
	//}
	
--%>


<%
BookTitleRepositoryMongoDB bktitleRepo=new BookTitleRepositoryMongoDB(DBConnect.getConnection());
BookTitle booktTile=bktitleRepo.findOne(request.getParameter("BookTitleId"));

out.print("[");

/* Outer loop for each row. */
//while(true)
//{               
	/* Starting of row of an array */
	out.print("{");
			/*  Inner loop for all columns */           
    //for(int i=1;i<=14;i++)
    	//{
        out.print("\"" + "bookTitleID" + "\":\""  + 
        booktTile.getBookTitleID() + "\"");
        out.print(",");
        
        out.print("\"" + "isbnNumber" + "\":\""  + 
                booktTile.getIsbnNumber() + "\"");
                out.print(",");
                
        out.print("\"" + "bookName" + "\":\""  + 
                        booktTile.getBookName() + "\"");
                        out.print(",");

        out.print("\"" + "author" + "\":\""  + 
                         booktTile.getAuthor()+ "\"");
                         out.print(",");
                
        out.print("\"" + "publication" + "\":\""  + 
                          booktTile.getPublication()+ "\"");
                          out.print(",");
                         
        out.print("\"" + "bindingType" + "\":\""  + 
                booktTile.getBindingType()+ "\"");
                out.print(",");           
        
        out.print("\"" + "tags" + "\":\""  + 
                        booktTile.getTags().toArray()+ "\"");
                        out.print(",");           
                
         out.print("\"" + "noOfPages" + "\":\""  + 
                                booktTile.getNoOfPages()+ "\"");
                                out.print(",");         
       
          out.print("\"" + "language" + "\":\""  + 
                                        booktTile.getLanguage()+ "\"");
                                        out.print(",");        
           
          out.print("\"" + "location" + "\":\""  + 
                                                booktTile.getLocation()+ "\"");
                                                out.print(",");       
                                                
        //if(i<numberOfColumns)
        	//out.print(",");
       // }
	/* Starting of row of an array */
    out.print("}");
    out.print("]");
    /* This if statement serves two purpose, first one is it's takes cursor to the next record and it's 
        also check whether it is not at EOF, if not it prints a comma and a newline, as you know 
        each row in json data is in curly braces and separated by comma. i.e. 
       {"Name":"Simon","Age":"26"}, and if reaches EOF prints the closing bracket of the array 
       and exists loop */
    //if(rs.next())
     //   out.print(",\n");
    //else
      //  {
        //out.print("]");
        //break;
        //}
//}

%>





</body>
</html>