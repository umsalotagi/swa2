package com.swapasya.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swapasya.domains.Book;
import com.swapasya.domains.BookTitle;
import com.swapasya.model.DBConnect;
import com.swapasya.repo.BookTitleRepositoryMongoDB;

/**
 * Servlet implementation class GetBooks
 */
@WebServlet("/getBooks")
public class GetBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		System.out.println("In get servlet get method");
		BookTitleRepositoryMongoDB bktitleRepo=new BookTitleRepositoryMongoDB(DBConnect.getConnection());
		BookTitle booktTile=bktitleRepo.findOne(request.getParameter("BookTitleId"));

		List<Book> listOfBooks=booktTile.getBooks();
		out.print("[");

		/* Outer loop for each row. */
		//while(true)
		//{               
			/* Starting of row of an array */
			
					/*  Inner loop for all columns */           
		    Iterator<Book> i=listOfBooks.iterator();
		    while(i.hasNext()){
		    	
		    		Book book=i.next();
		    		out.print("{");
		    	//{
			 out.print("\"" + "Book ID" + "\":\""  + 
				        book.getBookID() + "\"");
				        out.print(",");
				        
				        
				        out.print("\"" + "Borrowed By" + "\":\""  + 
				                book.getBorrowedBy() + "\"");
				                out.print(",");
				                
				        out.print("\"" + "Issued Type" + "\":\""  + 
				                        book.getIssuedType() + "\"");
				                        out.print(",");
				        out.print("},");
				       // }
					/* Starting of row of an array */
		    }
				    //out.print("}");
				    out.print("]");
				    
	}

}
