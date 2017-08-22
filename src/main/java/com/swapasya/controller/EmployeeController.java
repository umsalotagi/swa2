package com.swapasya.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.swapasya.domains.BookTitle;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    BookTitle bookTitle=new BookTitle();
    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
    public BookTitle getEmployeeInJSON(@PathVariable String name) {
       
       return bookTitle;
    }
}