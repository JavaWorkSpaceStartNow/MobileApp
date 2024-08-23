package com.mobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobile.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}
