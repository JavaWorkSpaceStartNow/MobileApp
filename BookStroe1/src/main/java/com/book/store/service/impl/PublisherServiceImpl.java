package com.book.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.model.Publisher;
import com.book.store.repositories.PublisherRepository;
import com.book.store.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	@Override
	public List<Publisher> findAll() {
		return publisherRepository.findAll();
	}

	@Override
	public Publisher findById(Long id) {
		Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
		return optionalPublisher.orElse(null);
	}

	@Override
	public Publisher save(Publisher publisher) {
		return publisherRepository.save(publisher);
	}

	@Override
	public void deleteById(Long id) {
		publisherRepository.deleteById(id);
	}
}