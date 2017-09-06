package com.swapasya.controller;

import java.util.List;

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
public class BookTitleController {

	@RequestMapping(value = "/search_book/{bookTitleID}", method = RequestMethod.GET)
	public MongoCursor<Document> searchBook(@PathVariable String search, @PathVariable String txt) {

		BookTitleRead bookTitleRead = new BookTitleRead("test4");
		if (search != null && txt != null) {
			switch (search) {
			case "Book_Title":
				return bookTitleRead.findByBookName(txt);
			// break;

			case "Book_TitleID":
				return bookTitleRead.findByBookTitleId2(txt);
			// break;

			case "Book_ID":
				return bookTitleRead.findByBookId2(txt);
			// break;

			case "Author":
				return bookTitleRead.findByAuthor(txt);
			// break;

			case "Publication":
				return bookTitleRead.findByPublication(txt);
			// break;

			case "Tag":
				return bookTitleRead.findByTag(txt);
			// break;
			}
		}
		return null;
	}

	// asking to show the the details of all bookTilte fields later on to save
	// it
	@RequestMapping(value = "/edit/{bookTitleID}", method = RequestMethod.GET)
	public Document showBookTitleFull(@PathVariable String bookTitleID) {

		BookTitleRead bookTitleRead = new BookTitleRead("test4");
		return bookTitleRead.findByBookTitleIdFULL(bookTitleID);

	}

	// updating bookTitle with new changes
	@RequestMapping(value = "/update/", method = RequestMethod.POST)
	public String updateBookTitle(@RequestBody Document bookTitle) {

		BookTitleRead bookTitleRead = new BookTitleRead("test4");
		bookTitleRead.updateBookTitle(bookTitle);
		return "success";
	}

	@RequestMapping(value = "/insert/", method = RequestMethod.POST)
	public String insertBookTitle(@RequestBody Document bookTitle) {

		BookTitleRead bookTitleRead = new BookTitleRead("test4");
		bookTitleRead.insertOne(bookTitle);
		return "success";
	}

	@RequestMapping(value = "/delete/{bookTitleID}", method = RequestMethod.DELETE)
	public String deleteBookTitle(@PathVariable String bookTitleID) {
		BookTitleRead bookTitleRead = new BookTitleRead("test4");
		bookTitleRead.delete(bookTitleID);
		return "success";

	}

	@RequestMapping(value = "/clearWaitList/{bookTitleID}", method = RequestMethod.POST)
	public String clearWaitList(@PathVariable String bookTitleID) {

		BookTitleRead bookTitleRead = new BookTitleRead("test4");
		bookTitleRead.clearWaitList(bookTitleID);
		return "success";
	}

	@RequestMapping(value = "/clearAssignList/{bookTitleID}", method = RequestMethod.POST)
	public String clearAssignList(@PathVariable String bookTitleID) {

		BookTitleRead bookTitleRead = new BookTitleRead("test4");
		bookTitleRead.clearAssignList(bookTitleID);
		return "success";
	}

	@RequestMapping(value = "/addBookInBkTitle/{bookTitleID}", method = RequestMethod.POST)
	public String addBookInBkTitle(@PathVariable String bookTitleID, @PathVariable Document book) {

		BookTitleRead bookTitleRead = new BookTitleRead("test4");
		bookTitleRead.addBookToBookTitle(bookTitleID, book);
		return "success";
	}

	@RequestMapping(value = "/removeBooksFromBkTitle/{bookTitleID}", method = RequestMethod.POST)
	public String removeBooksFromBkTitle(@PathVariable String bookTitleID, @PathVariable List<String> bookIDs) {

		BookTitleRead bookTitleRead = new BookTitleRead("test4");

		for (String bookID : bookIDs) {
			bookTitleRead.removeBookFromBookTitle(bookTitleID, bookID);
		}

		return "success";
	}

}
