package com.flab.idolu.domain.member.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.flab.idolu.domain.member.dto.request.SignUpMemberDto;
import com.flab.idolu.domain.member.repository.MemberRepository;

@Transactional
@SpringBootTest
class EmailDuplicateTest {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberService memberService;

	@Test
	void concurrencyTest() {
		assertThatThrownBy(() -> {
			ExceptionThread thread1 = new ExceptionThread(
				() -> memberService.signUp(DEFAULT_SIGNUP_MEMBER));
			ExceptionThread thread2 = new ExceptionThread(
				() -> memberService.signUp(DEFAULT_SIGNUP_MEMBER));

			thread1.start();
			thread2.start();
			thread1.join();
			thread2.join();
			thread1.checkException();
			thread2.checkException();
		}).isInstanceOf(DuplicateKeyException.class);
	}

	private static final SignUpMemberDto DEFAULT_SIGNUP_MEMBER = SignUpMemberDto.builder()
		.email("testUser@email.com")
		.password("testPassword1")
		.passwordConfirm("testPassword1")
		.name("testUser1")
		.phone("01011111111")
		.role("USER")
		.build();
}
