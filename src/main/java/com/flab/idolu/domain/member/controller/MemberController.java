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
import com.flab.idolu.domain.member.service.MemberService;
import com.flab.idolu.global.annotation.MemberLoginCheck;
import com.flab.idolu.global.common.ResponseMessage;
import com.flab.idolu.global.util.SessionUtil;

import jakarta.servlet.http.HttpSession;
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

	@MemberLoginCheck
	@GetMapping("/myInfo")
	public ResponseEntity<ResponseMessage> findMyInfo(HttpSession session) {
		Long memberId = SessionUtil.getLoginMemberId(session);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.result(memberService.getMemberInfo(memberId))
			.build());
	}

	@MemberLoginCheck
	@PatchMapping("/myInfo/edit")
	public ResponseEntity<ResponseMessage> modifyMyInfo(HttpSession session,
		@RequestBody ModifyMemberDto modifyMemberDto) {

		Long memberId = SessionUtil.getLoginMemberId(session);
		memberService.modifyMemberInfo(modifyMemberDto, memberId);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}

	@MemberLoginCheck
	@PatchMapping("/withdraw")
	public ResponseEntity<ResponseMessage> withdrawMember(HttpSession session) {
		Long memberId = SessionUtil.getLoginMemberId(session);
		memberService.withdrawMember(memberId);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}
}
