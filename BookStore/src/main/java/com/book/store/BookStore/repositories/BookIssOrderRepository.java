package com.book.store.BookStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.store.BookStore.model.BookIssOrder;

public interface BookIssOrderRepository extends JpaRepository<BookIssOrder, Long> {
}