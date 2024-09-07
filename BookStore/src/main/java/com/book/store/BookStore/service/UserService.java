package com.book.store.BookStore.service;

import java.util.List;

import com.book.store.BookStore.model.User;

public interface UserService {

	String verify(User user);

	List<User> findAll();

	User findById(Long id);

	User save(User user);

	void deleteById(Long id);

	User update(Long idO, User userDetails);

}
