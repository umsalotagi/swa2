package com.swapasya.controller;

import org.bson.Document;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.client.MongoCursor;
import com.swapasya.repo.BookTitleRead;


@RestController
@RequestMapping("/bookTitle")
public class BookTitleController
{
	//searching by bookTitleID
	@RequestMapping(value="/searchBy/{bookTitleID}",method=RequestMethod.GET)
	public Document getByBookTitleID(@PathVariable String bookTitleID)
	{
		Document bookTitleDoc=null;
		BookTitleRead bookTitleRead=new BookTitleRead("test4");
		bookTitleDoc=bookTitleRead.findByBookTitleId(bookTitleID);
		return bookTitleDoc;
		
	}
	
	//searching by bookTitleTag
	/*	@RequestMapping(value="/searchBy/{tag}",method=RequestMethod.GET)
		public MongoCursor<Document> getByBookTitleTag(@PathVariable String tag)
		{
			MongoCursor<Document> bookTitleDocs=null;
			BookTitleRead bookTitleRead=new BookTitleRead("test4");
			bookTitleDocs=bookTitleRead.findByTag(tag);
			return bookTitleDocs;
			
		}
		
		//searching by bookID
		@RequestMapping(value="/searchBy/{bookID}",method=RequestMethod.GET)
		public Document getByBookID(@PathVariable String bookID)
		{
			Document bookDoc=null;
			BookTitleRead bookTitleRead=new BookTitleRead("test4");
			bookDoc=bookTitleRead.findByBookId(bookID);
			return bookDoc;
			
		}
		
		
		//searching by bookAuthor
				@RequestMapping(value="/searchBy/{author}",method=RequestMethod.GET)
				public MongoCursor<Document> getByBookTitleAuthor(@PathVariable String author)
				{
					MongoCursor<Document> bookTitleDocs=null;
					BookTitleRead bookTitleRead=new BookTitleRead("test4");
					bookTitleDocs=bookTitleRead.findByAuthor(author);
					return bookTitleDocs;
					
				}
	
				//searching by bookPublication
				@RequestMapping(value="/searchBy/{publication}",method=RequestMethod.GET)
				public MongoCursor<Document> getByBookTitlePublication(@PathVariable String publication)
				{
					MongoCursor<Document> bookTitleDocs=null;
					BookTitleRead bookTitleRead=new BookTitleRead("test4");
					bookTitleDocs=bookTitleRead.findByPublication(publication);
					return bookTitleDocs;
					
				}
	*/
				//asking to show the the deatils of all bookTilte fields later on to save it
				@RequestMapping(value="/edit/{bookTitleID}",method=RequestMethod.GET)
				public Document showBookTitleFull(@PathVariable String bookTitleID)
				{
					Document bookTitleDoc=null;
					BookTitleRead bookTitleRead=new BookTitleRead("test4");
					bookTitleDoc=bookTitleRead.findByBookTitleIdFULL(bookTitleID);
					return bookTitleDoc;
					
				}
				
				
				//saving bookTitle with new changes
				@RequestMapping(value="/save/",method=RequestMethod.POST)
				public String saveBookTitle(@RequestBody Document bookTitle)
				{
					
					BookTitleRead bookTitleRead=new BookTitleRead("test4");
					bookTitleRead.insertOne(bookTitle);
					return "success";
				}
				
				
				
				@RequestMapping(value="/delete/{bookTitleID}",method=RequestMethod.DELETE)
				public String deleteBookTitle(@PathVariable String bookTitleID)
				{
					BookTitleRead bookTitleRead=new BookTitleRead("test4");
					bookTitleRead.delete(bookTitleID);
					return "success";
					
				}
	
}




/* code of controller may be uses in jsp like this
@Controller
@RequestMapping("bookTitle")
public class BookTitleController {
    BookTitle bookTitle=new BookTitle();
    @RequestMapping(value = "/{bookTitleID}", method = RequestMethod.GET)//, produces = "application/json")
    public  String getBookTitleInJSON(@PathVariable String bookTitleID,ModelMap model) {
       
    	bookTitle.setBookName("ASP .net");
    	/*String json=bookTitle.toString();
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
    
	@RequestMapping(value = "/search_book/{bookTitleID}", method = RequestMethod.GET)
	public String searchBook(@RequestParam("search") String search, @RequestParam("txt") String txt,ModelMap model) {
		
		Document bookTitleDoc=null;
		BookTitleRead bookTitleRead=new BookTitleRead("test4");
		if (search != null && txt != null) {
			switch (search) {
			case "Book Title":
				 bookTitleDoc= bookTitleRead.findByBookTitleId(txt);
				break;
/*
			case "Book ID":
				System.out.println(" before In Book ID case" + search);
				//bookTitles = bookTitleRead.findBy
				System.out.println(" After In Book ID case");
				break;

			case "Author":

				bookTitles = bktitlerepo.findByAuthorRegex(txt);
				break;

			case "Publication":
				System.out.println(" before In Book publication case" + search);
				bookTitles = bktitlerepo.findByPublicationRegex(txt);
				System.out.println(" after In Book publication case" + search);
				break;

			case "Tag":
				bookTitles = bktitlerepo.findByTag(txt);
				break;
			}
		}
		model.addAttribute("bookTitleDoc",bookTitleDoc);
		return "book_related2";
	}

    
}*/