package com.flab.idolu.domain.member.controller;

import static com.flab.idolu.global.common.ResponseMessage.Status.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.idolu.domain.member.dto.request.LoginMemberDto;
import com.flab.idolu.domain.member.dto.request.ModifyMemberDto;
import com.flab.idolu.domain.member.dto.request.SignUpMemberDto;
import com.flab.idolu.domain.member.entity.Member;
import com.flab.idolu.domain.member.service.MemberService;
import com.flab.idolu.global.annotation.MemberLoginCheck;
import com.flab.idolu.global.annotation.SessionMemberId;
import com.flab.idolu.global.common.ResponseMessage;
import com.flab.idolu.global.util.SessionManager;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final SessionManager sessionManager;

	@PostMapping("/signup")
	public ResponseEntity<ResponseMessage> signup(@RequestBody SignUpMemberDto signUpMemberDto) {
		memberService.signUp(signUpMemberDto);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseMessage> login(@RequestBody LoginMemberDto loginMemberDto) {
		Member member = memberService.login(loginMemberDto);
		sessionManager.setLoginMemberId(member.getId());

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}

	@GetMapping("/logout")
	public ResponseEntity<ResponseMessage> logout() {
		sessionManager.removeLoginMemberId();

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}

	@MemberLoginCheck
	@GetMapping("/myInfo")
	public ResponseEntity<ResponseMessage> findMyInfo(@SessionMemberId Long memberId) {

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.result(memberService.getMemberInfo(memberId))
			.build());
	}

	@MemberLoginCheck
	@PatchMapping("/myInfo/edit")
	public ResponseEntity<ResponseMessage> modifyMyInfo(
		@RequestBody ModifyMemberDto modifyMemberDto,
		@SessionMemberId Long memberId
	) {

		memberService.modifyMemberInfo(modifyMemberDto, memberId);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}

	@MemberLoginCheck
	@PatchMapping("/withdraw")
	public ResponseEntity<ResponseMessage> withdrawMember(@SessionMemberId Long memberId) {
		memberService.withdrawMember(memberId);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}
}
