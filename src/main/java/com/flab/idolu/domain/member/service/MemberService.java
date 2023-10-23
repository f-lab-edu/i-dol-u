package com.flab.idolu.domain.member.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.flab.idolu.domain.member.dto.request.SignUpMemberDto;
import com.flab.idolu.domain.member.exception.EmailDuplicateException;
import com.flab.idolu.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	/**
	 * 회원가입 메서드
	 * @param signUpMemberDto 사용자 입력 정보
	 * @return
	 */
	public Long signUp(SignUpMemberDto signUpMemberDto) {
		validateMemberDto(signUpMemberDto);
		if (isDuplicatedMember(signUpMemberDto)) {
			throw new EmailDuplicateException("이미 가입한 이메일입니다. 입력된 이메일: %s".formatted(signUpMemberDto.getEmail()));
		}

		return memberRepository.insertMember(signUpMemberDto.toEntity());
	}

	private boolean isDuplicatedMember(SignUpMemberDto signUpMemberDto) {
		return memberRepository.findByEmail(signUpMemberDto.getEmail()).isPresent();
	}

	private void validateMemberDto(SignUpMemberDto signUpMemberDto) {
		Assert.hasText(signUpMemberDto.getEmail(), "이메일을 입력해야 합니다.");
		Assert.hasText(signUpMemberDto.getPassword(), "비밀번호를 입력해야 합니다.");
		Assert.hasText(signUpMemberDto.getPasswordConfirm(), "비밀번호 확인을 입력해야 합니다.");
		Assert.hasText(signUpMemberDto.getName(), "이름을 입력해야 합니다.");
		Assert.hasText(signUpMemberDto.getPhone(), "휴대전화를 입력해야 합니다.");
		Assert.hasText(signUpMemberDto.getRole(), "역할을 입력해야 합니다.");

		Assert.isTrue(
			Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
				signUpMemberDto.getEmail()), "이메일 양식에 맞춰야 합니다. 현재 이메일: %s".formatted(signUpMemberDto.getEmail()));
		Assert.isTrue(
			Pattern.matches("^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}", signUpMemberDto.getPassword()),
			"비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다. 현재 비밀번회: %s".formatted(signUpMemberDto.getPassword()));
		Assert.isTrue(
			signUpMemberDto.getPassword().equals(signUpMemberDto.getPasswordConfirm()),
			"비밀번호와 비밀번호 확인이 일치해야 합니다. 비밀번호: %s, 비밀번호 확인: %s".formatted(
				signUpMemberDto.getPassword(),
				signUpMemberDto.getPasswordConfirm()));
	}
}
