package com.flab.idolu.domain.member.controller;

import static com.flab.idolu.global.common.ResponseMessage.Status.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return ResponseEntity.status(OK)
			.body(new ResponseMessage.Builder(SUCCESS)
				.build());
	}
}
