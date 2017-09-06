package com.swapasya.controller;

import java.io.IOException;

import org.bson.json.JsonParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapasya.domains.BookTitle;

@Controller
@RequestMapping("bookTitle")
public class BkTitleController {
	
	
	    BookTitle bookTitle=new BookTitle();
	    @RequestMapping(value = "/{bookTitleID}", method = RequestMethod.GET)//, produces = "application/json")
	    public  String getBookTitleInJSON(@PathVariable String bookTitleID,ModelMap model) {
	       
	    	bookTitle.setBookName("ASP .net");
	    	String json=bookTitle.toString();
	    	ObjectMapper mapper = new ObjectMapper();
	    	 
	    	//Convert JSON to POJO
	    	try {
				bookTitle= mapper.readValue(json, BookTitle.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	model.addAttribute("bookTitle", bookTitle.getBookName());
	       return "sampleJSON";
	    }
	    
	    @RequestMapping(value="/")
	    public String getBookRelated()
	    {
	    	
	    	return "book_related2";
	    }
	    
		
	

}
