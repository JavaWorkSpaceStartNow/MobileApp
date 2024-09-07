package com.book.store.BookStore.service;

import java.util.List;

import com.book.store.BookStore.model.BookIssOrder;

public interface BookIssOrderService {

	List<BookIssOrder> findAll();

	BookIssOrder findById(Long id);

	BookIssOrder save(BookIssOrder bookIssOrder);

	void deleteById(Long id);

}
