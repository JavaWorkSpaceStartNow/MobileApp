package com.book.store.service;

import java.util.List;

import com.book.store.model.Book;

public interface BookService {

	List<Book> findAll();

	Book findById(Long id);

	Book save(Book book);

	void deleteById(Long id);
}
