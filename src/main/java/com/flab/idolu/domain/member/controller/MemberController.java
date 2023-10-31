package com.flab.idolu.domain.member.controller;

import static com.flab.idolu.global.common.ResponseMessage.Status.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.idolu.domain.member.dto.request.LoginMemberDto;
import com.flab.idolu.domain.member.dto.request.SignUpMemberDto;
import com.flab.idolu.domain.member.service.MemberService;
import com.flab.idolu.global.common.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/signup")
	public ResponseEntity<ResponseMessage> signup(@RequestBody SignUpMemberDto signUpMemberDto) {
		memberService.signUp(signUpMemberDto);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseMessage> login(@RequestBody LoginMemberDto loginMemberDto) {
		memberService.login(loginMemberDto);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}

	@GetMapping("/logout")
	public ResponseEntity<ResponseMessage> logout() {
		memberService.logout();

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}
}
