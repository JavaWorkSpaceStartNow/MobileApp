package com.book.store.service;

import java.util.List;

import com.book.store.model.BookIssue;

public interface BookIssueService {

	List<BookIssue> findAll();

	BookIssue findById(Long id);

	BookIssue save(BookIssue bookIssue);

	void deleteById(Long id);

}
