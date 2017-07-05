package com.swapasya.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swapasya.domains.BookTitle;
import com.swapasya.model.DBConnect;
import com.swapasya.repo.BookTitleRepositoryMongoDB;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		System.out.println("In select servlet get method");
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
				        out.print("},{");
				        
				        
				        out.print("\"" + "isbnNumber" + "\":\""  + 
				                booktTile.getIsbnNumber() + "\"");
				                out.print("},{");
				                
				        out.print("\"" + "bookName" + "\":\""  + 
				                        booktTile.getBookName() + "\"");
				                        out.print("},{");

				        out.print("\"" + "author" + "\":\""  + 
				                         booktTile.getAuthor()+ "\"");
				                         out.print("},{");
				                
				        out.print("\"" + "publication" + "\":\""  + 
				                          booktTile.getPublication()+ "\"");
				                          out.print("},{");
				                         
				        out.print("\"" + "bindingType" + "\":\""  + 
				                booktTile.getBindingType()+ "\"");
				                out.print("},{");           
				        
				                ArrayList<String> tags=booktTile.getTags();
				                Iterator<String> tagIterator=tags.iterator();
				                StringBuffer sb=new StringBuffer();
				                 
				                while(tagIterator.hasNext())
				                {
				                	
				                	sb.append(tagIterator.next()+" ");
				                }
				        out.print("\"" + "tags" + "\":\""  + 
				                        sb.toString()+ "\"");
				                        out.print("},{");           
				                
				         out.print("\"" + "noOfPages" + "\":\""  + 
				                                booktTile.getNoOfPages()+ "\"");
				                                out.print("},{");         
				       
				          out.print("\"" + "language" + "\":\""  + 
				                                        booktTile.getLanguage()+ "\"");
				                                        out.print("},{");        
				           
				          out.print("\"" + "location" + "\":\""  + 
				                                                booktTile.getLocation()+ "\"");
				                  
				                                                
				        //if(i<numberOfColumns)
				        	//out.print(",");
				       // }
					/* Starting of row of an array */
				    out.print("}");
				    out.print("]");
				    

	}

}
