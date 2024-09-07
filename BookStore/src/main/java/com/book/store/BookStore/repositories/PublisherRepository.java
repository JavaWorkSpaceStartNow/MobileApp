package com.book.store.BookStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.store.BookStore.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}