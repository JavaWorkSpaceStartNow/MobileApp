package com.mobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobile.model.Book;

@Repository
public interface BookRepositery extends JpaRepository<Book, Integer> {

}
