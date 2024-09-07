package com.book.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.model.BookIssOrder;
import com.book.store.repositories.BookIssOrderRepository;
import com.book.store.service.BookIssOrderService;

@Service
public class BookIssOrderServiceImpl implements BookIssOrderService {

	@Autowired
	private BookIssOrderRepository bookIssOrderRepository;

	@Override
	public List<BookIssOrder> findAll() {
		return bookIssOrderRepository.findAll();
	}

	@Override
	public BookIssOrder findById(Long id) {
		Optional<BookIssOrder> optionalBookIssOrder = bookIssOrderRepository.findById(id);
		return optionalBookIssOrder.orElse(null);
	}

	@Override
	public BookIssOrder save(BookIssOrder bookIssOrder) {
		return bookIssOrderRepository.save(bookIssOrder);
	}

	@Override
	public void deleteById(Long id) {
		bookIssOrderRepository.deleteById(id);
	}
}