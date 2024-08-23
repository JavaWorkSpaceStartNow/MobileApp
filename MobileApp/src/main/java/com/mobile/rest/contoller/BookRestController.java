package com.mobile.rest.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.model.Book;
import com.mobile.service.BookService;
import com.mobile.vo.BookVo;

@RestController
@RequestMapping("/book")
public class BookRestController {

	@Autowired
	private BookService bookService;

	@PostMapping("/register")
	public Book register(@RequestBody BookVo bookVo) {
		return bookService.register(bookVo);
	}

	@PostMapping("/update")
	public Book update(@RequestBody BookVo bookVo) {
		return bookService.update(bookVo);
	}

	@GetMapping("/delete")
	public String delete(@RequestParam int bookId) {
		try {
			bookService.delete(bookId);
		} catch (Exception e) {
			return "Book Not Deleted";
		}
		return "Book Deleted Successfully";
	}

}