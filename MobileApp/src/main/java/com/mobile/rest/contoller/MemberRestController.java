package com.mobile.rest.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.model.Member;
import com.mobile.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberRestController {

	@Autowired
	private MemberService memberService;

	@PostMapping("/login")
	public String login(@RequestBody Member member) {
		return memberService.verify(member);
	}

}
