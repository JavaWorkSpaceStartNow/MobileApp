package com.book.store.BookStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.store.BookStore.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

	Roles findByName(String role);

}
