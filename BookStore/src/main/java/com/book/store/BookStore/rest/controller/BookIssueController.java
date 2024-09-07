package com.book.store.BookStore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.store.BookStore.model.BookIssue;
import com.book.store.BookStore.service.BookIssueService;

@RestController
@RequestMapping("/api/bookIssues")
public class BookIssueController {

	@Autowired
	private BookIssueService bookIssueService;

	@GetMapping
	public ResponseEntity<List<BookIssue>> getAllBookIssues() {
		return ResponseEntity.ok(bookIssueService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookIssue> getBookIssueById(@PathVariable Long id) {
		BookIssue bookIssue = bookIssueService.findById(id);
		if (bookIssue != null) {
			return ResponseEntity.ok(bookIssue);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping
	public ResponseEntity<BookIssue> createBookIssue(@RequestBody BookIssue bookIssue) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bookIssueService.save(bookIssue));
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookIssue> updateBookIssue(@PathVariable Long id, @RequestBody BookIssue bookIssueDetails) {
		BookIssue bookIssue = bookIssueService.findById(id);
		if (bookIssue != null) {
			bookIssue.setBook(bookIssueDetails.getBook());
			bookIssue.setUser(bookIssueDetails.getUser()); // Updated from setMember to setUser
			bookIssue.setIssueDate(bookIssueDetails.getIssueDate());
			bookIssue.setReturnDate(bookIssueDetails.getReturnDate());
			bookIssue.setDateReturned(bookIssueDetails.getDateReturned());
			bookIssue.setBorrowAmount(bookIssueDetails.getBorrowAmount());
			bookIssue.setLateReturnFeeAmount(bookIssueDetails.getLateReturnFeeAmount());
			bookIssue.setBookIssOrder(bookIssueDetails.getBookIssOrder());
			return ResponseEntity.ok(bookIssueService.save(bookIssue));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBookIssue(@PathVariable Long id) {
		bookIssueService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
