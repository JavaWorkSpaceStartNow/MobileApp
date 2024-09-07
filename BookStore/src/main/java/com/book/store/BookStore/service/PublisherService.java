package com.book.store.BookStore.service;

import java.util.List;

import com.book.store.BookStore.model.Publisher;

public interface PublisherService {

	List<Publisher> findAll();

	Publisher findById(Long id);

	Publisher save(Publisher publisher);

	void deleteById(Long id);
}
