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

import com.book.store.BookStore.model.BookIssOrder;
import com.book.store.BookStore.service.BookIssOrderService;

@RestController
@RequestMapping("/api/bookIssOrders")
public class BookIssOrderController {

	@Autowired
	private BookIssOrderService bookIssOrderService;

	@GetMapping
	public ResponseEntity<List<BookIssOrder>> getAllBookIssOrders() {
		return ResponseEntity.ok(bookIssOrderService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookIssOrder> getBookIssOrderById(@PathVariable Long id) {
		BookIssOrder bookIssOrder = bookIssOrderService.findById(id);
		if (bookIssOrder != null) {
			return ResponseEntity.ok(bookIssOrder);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping
	public ResponseEntity<BookIssOrder> createBookIssOrder(@RequestBody BookIssOrder bookIssOrder) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bookIssOrderService.save(bookIssOrder));
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookIssOrder> updateBookIssOrder(@PathVariable Long id,
			@RequestBody BookIssOrder bookIssOrderDetails) {
		BookIssOrder bookIssOrder = bookIssOrderService.findById(id);
		if (bookIssOrder != null) {
			bookIssOrder.setTotalAmount(bookIssOrderDetails.getTotalAmount());
			bookIssOrder.setOrderStatus(bookIssOrderDetails.getOrderStatus());
			return ResponseEntity.ok(bookIssOrderService.save(bookIssOrder));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBookIssOrder(@PathVariable Long id) {
		bookIssOrderService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}