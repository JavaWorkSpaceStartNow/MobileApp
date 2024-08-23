package com.mobile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mobile.config.JWTService;
import com.mobile.model.Member;
import com.mobile.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private JWTService jwtService;

	@Autowired
	AuthenticationManager authManager;

	public String verify(Member member) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(member.getUsername());
		} else {
			return "fail";
		}
	}

}
