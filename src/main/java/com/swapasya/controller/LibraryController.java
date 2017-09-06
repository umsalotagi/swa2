package com.swapasya.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.swapasya.domains.BookTitle;
import com.swapasya.model.DBConnect;
import com.swapasya.repo.BookTitleRepositoryMongoDB;

@Controller
@Deprecated
public class LibraryController {

	@RequestMapping(value = "/book_related1.html", method = RequestMethod.GET)
	public ModelAndView getBookRelated() {

		System.out.println("In book_related.html mapping");
		ModelAndView model = new ModelAndView("book_related2");
		return model;
	}

	@RequestMapping(value = "/form_editbook.html", method = RequestMethod.GET)
	public ModelAndView getFormEditbook(@RequestParam("bookTitleID") String bookTitleID) {

		System.out.println("In editbook.html mapping");
		ModelAndView model = new ModelAndView("form_editbook");

		BookTitle bkTitle;
		BookTitleRepositoryMongoDB bkrepo = new BookTitleRepositoryMongoDB(DBConnect.getConnection());
		bkTitle = bkrepo.findOne(bookTitleID);

		model.addObject("bookTitle", bkTitle);
		return model;
	}

	@RequestMapping(value = "/save_book.html", method = RequestMethod.POST)
	public ModelAndView saveBook(@ModelAttribute("BookTitle") BookTitle bookTitle) {

		System.out.println("In save.html mapping");

		ModelAndView model = new ModelAndView("book_related2");

		BookTitleRepositoryMongoDB bktitlerepo = new BookTitleRepositoryMongoDB(DBConnect.getConnection());
		bktitlerepo.save(bookTitle);

		model.addObject("msg", "Book details are updated successfully");
		return model;
	}

	@RequestMapping(value = "/search_book.html", method = RequestMethod.GET)
	public ModelAndView searchBook(@RequestParam("search") String search, @RequestParam("txt") String txt) {
		ModelAndView model = new ModelAndView("book_related2");
		List<BookTitle> bookTitles = null;
		BookTitleRepositoryMongoDB bktitlerepo = new BookTitleRepositoryMongoDB(DBConnect.getConnection());
		if (search != null && txt != null) {
			switch (search) {
			case "Book Title":
				bookTitles = bktitlerepo.findByBookTitleRegex(txt);
				break;

			case "Book ID":
				System.out.println(" before In Book ID case" + search);
				bookTitles = bktitlerepo.findByBookId(txt);
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
		model.addObject("bookTitles", bookTitles);
		return model;
	}

}
