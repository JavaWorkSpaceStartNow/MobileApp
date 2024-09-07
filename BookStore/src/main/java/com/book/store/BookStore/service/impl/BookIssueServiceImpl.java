package com.book.store.BookStore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.BookStore.model.BookIssue;
import com.book.store.BookStore.repositories.BookIssueRepository;
import com.book.store.BookStore.service.BookIssueService;

@Service
public class BookIssueServiceImpl implements BookIssueService {

	@Autowired
	private BookIssueRepository bookIssueRepository;

	@Override
	public List<BookIssue> findAll() {
		return bookIssueRepository.findAll();
	}

	@Override
	public BookIssue findById(Long id) {
		Optional<BookIssue> optionalBookIssue = bookIssueRepository.findById(id);
		return optionalBookIssue.orElse(null);
	}

	@Override
	public BookIssue save(BookIssue bookIssue) {
		return bookIssueRepository.save(bookIssue);
	}

	@Override
	public void deleteById(Long id) {
		bookIssueRepository.deleteById(id);
	}
}
