package com.book.store.BookStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.store.BookStore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
