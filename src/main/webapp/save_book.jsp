<%@page import="com.swapasya.domains.BookTitle"%>
<%@page import="com.swapasya.model.DBConnect"%>
<%@page import="com.swapasya.repo.BookTitleRepositoryMongoDB"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
BookTitleRepositoryMongoDB bkrepo=new BookTitleRepositoryMongoDB(DBConnect.getConnection());
BookTitle bk1=bkrepo.save((BookTitle)request.getAttribute("bookTitle"));

if(bk1!=null){

System.out.println("Updated Successfully...");
}

%>
<%--
<jsp:useBean id="bkTitle" class="com.swapasya.domains.BookTitle"></jsp:useBean>
<jsp:setProperty property="*" name="bkTitle"/>
<%
BookTitleRepositoryMongoDB bkrepo=new BookTitleRepositoryMongoDB(DBConnect.getConnection());
BookTitle bk1=bkrepo.save(bkTitle);
if(bk1!=null){
%>
Updated Successfully...
<jsp:include page="book_related.jsp"></jsp:include>
<%} %>
 --%>
</body>
</html>