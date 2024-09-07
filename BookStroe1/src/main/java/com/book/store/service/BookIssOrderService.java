package com.book.store.service;

import java.util.List;

import com.book.store.model.BookIssOrder;

public interface BookIssOrderService {
	List<BookIssOrder> findAll();

	BookIssOrder findById(Long id);

	BookIssOrder save(BookIssOrder bookIssOrder);

	void deleteById(Long id);
}
