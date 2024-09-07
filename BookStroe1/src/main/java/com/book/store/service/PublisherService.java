package com.book.store.service;

import java.util.List;

import com.book.store.model.Publisher;

public interface PublisherService {

	List<Publisher> findAll();

	Publisher findById(Long id);

	Publisher save(Publisher publisher);

	void deleteById(Long id);
}