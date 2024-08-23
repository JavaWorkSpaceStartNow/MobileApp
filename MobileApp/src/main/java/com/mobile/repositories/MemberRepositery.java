package com.mobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobile.model.Member;
import com.mobile.model.Publisher;

@Repository
public interface MemberRepositery extends JpaRepository<Member, Integer> {

	Member findByUsername(String username);

}
