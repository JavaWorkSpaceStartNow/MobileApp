package com.book.store.BookStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.store.BookStore.model.BookIssue;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {
}
