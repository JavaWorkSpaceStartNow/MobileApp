package com.mobile.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mobile.model.Member;
import com.mobile.repositories.MemberRepositery;

@Service
public class MyMemberDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepositery memberRepositery;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepositery.findByUsername(username);
		if (member == null) {
			System.out.println("Member Not Found");
			throw new UsernameNotFoundException("Member not found");
		}

		return new MemberPrincipal(member);
	}
}