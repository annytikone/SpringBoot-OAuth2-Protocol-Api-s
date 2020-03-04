package com.controllers;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.book;
import com.model.daoBooks;
import com.repository.UserBooksRepository;

@RestController
//@EntityScan(basePackages = "com.books")
@ComponentScan(basePackages = { "com.books" })
public class BookController {
	@Autowired
	daoBooks dao;

	@Autowired(required = true)
	UserBooksRepository bookRepo;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/api/book/{id}")
	public Optional<book> singleInfo(@PathVariable("id") int id) {

		/*
		 * List<book> books = dao.singleData(id); return books;
		 */
		return bookRepo.findById(id);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("apis/getinfoByBookName/{bookname}")
	public List<book> bookInformationByname(@PathVariable("bookname") String bookname) {
		/*
		 * List<book> books = dao.getBookByName(bookname); return books;
		 */
		System.out.println("Just got a hit");
		return bookRepo.findBybookname(bookname);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("apis/getAllBooksInfo")
	public List<book> bookInformation() {
		/*
		 * List<book> books = dao.isData(); return books;
		 */
		return bookRepo.findAll();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping("api/update/book/{bookname}/{authorname}/{booktype}/{price}")
	public book bookInsertion(/* @PathVariable("id") int id */ @PathVariable("bookname") String bookname,
			@PathVariable("authorname") String authorname, @PathVariable("booktype") String booktype,
			@PathVariable("price") Double price) {
		book b = new book();
		// b.setId(id);
		b.setBookname(bookname);
		b.setAuthorname(authorname);
		b.setBooktype(booktype);
		b.setPrice(price);
		/*
		 * if (dao.savebook(b) == 1) { return "Successfully added to db"; } return
		 * "failed to add";
		 */
		return bookRepo.save(b);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping("api/del/book/{bookname}")
	public String deleteBookByName(@PathVariable("bookname") String bookname) {

		if (bookRepo.deleteBookByName(bookname) == 1) {
			return "deleted successfully";
		}
		return "no record found";
	}

}
