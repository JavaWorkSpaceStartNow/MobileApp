package com.book.store.BookStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.store.BookStore.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
