package com.flab.idolu.domain.fixture;

import com.flab.idolu.domain.member.dto.request.SignUpMemberDto;

public class MemberFixture {

	public static final SignUpMemberDto DEFAULT_SIGNUP_MEMBER = SignUpMemberDto.builder()
		.email("testUser@email.com")
		.password("testPassword1")
		.passwordConfirm("testPassword1")
		.name("testUser1")
		.phone("01011111111")
		.role("USER")
		.build();

	public static final SignUpMemberDto BLANK_EMAIL_MEMBER = SignUpMemberDto.builder()
		.email("")
		.password("testPassword1")
		.passwordConfirm("testPassword1")
		.name("testUser1")
		.phone("01011111111")
		.role("USER")
		.build();

	public static final SignUpMemberDto BLANK_PASSWORD_MEMBER = SignUpMemberDto.builder()
		.email("testUser@email.com")
		.password("")
		.passwordConfirm("testPassword1")
		.name("testUser1")
		.phone("01011111111")
		.role("USER")
		.build();

	public static final SignUpMemberDto BLANK_PASSWORD_CONFIRM_MEMBER = SignUpMemberDto.builder()
		.email("testUser@email.com")
		.password("testPassword1")
		.passwordConfirm("")
		.name("testUser1")
		.phone("01011111111")
		.role("USER")
		.build();

	public static final SignUpMemberDto BLANK_NAME_MEMBER = SignUpMemberDto.builder()
		.email("testUser@email.com")
		.password("testPassword1")
		.passwordConfirm("testPassword1")
		.name("")
		.phone("01011111111")
		.role("USER")
		.build();

	public static final SignUpMemberDto BLANK_PHONE_MEMBER = SignUpMemberDto.builder()
		.email("testUser@email.com")
		.password("testPassword1")
		.passwordConfirm("testPassword1")
		.name("testUser1")
		.phone("")
		.role("USER")
		.build();

	public static final SignUpMemberDto BLANK_ROLE_MEMBER = SignUpMemberDto.builder()
		.email("testUser@email.com")
		.password("testPassword1")
		.passwordConfirm("testPassword1")
		.name("testUser1")
		.phone("01011111111")
		.role("")
		.build();

	public static final SignUpMemberDto INVALID_EMAIL_MEMBER = SignUpMemberDto.builder()
		.email("testUser")
		.password("testPassword1")
		.passwordConfirm("testPassword1")
		.name("testUser1")
		.phone("01011111111")
		.role("USER")
		.build();

	public static final SignUpMemberDto INVALID_PASSWORD_MEMBER = SignUpMemberDto.builder()
		.email("testUser@email.com")
		.password("test")
		.passwordConfirm("testPassword1")
		.name("testUser1")
		.phone("01011111111")
		.role("USER")
		.build();

	public static final SignUpMemberDto NOT_MATCHED_PASSWORD_MEMBER = SignUpMemberDto.builder()
		.email("testUser@email.com")
		.password("testPassword1")
		.passwordConfirm("testPassword")
		.name("testUser1")
		.phone("01011111111")
		.role("USER")
		.build();
}
