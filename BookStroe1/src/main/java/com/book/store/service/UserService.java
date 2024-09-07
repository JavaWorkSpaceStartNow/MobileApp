package com.book.store.service;

import java.util.List;

import com.book.store.model.User;

public interface UserService {
    String verify(User user);

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    void deleteById(Long id);


}
