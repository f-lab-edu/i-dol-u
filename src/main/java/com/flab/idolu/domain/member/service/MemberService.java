package com.flab.idolu.domain.member.service;

import static com.flab.idolu.global.util.ValidateDtoUtil.*;

import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.flab.idolu.domain.member.dto.request.LoginMemberDto;
import com.flab.idolu.domain.member.dto.request.ModifyMemberDto;
import com.flab.idolu.domain.member.dto.request.SignUpMemberDto;
import com.flab.idolu.domain.member.dto.response.MyInfoMemberDto;
import com.flab.idolu.domain.member.entity.Member;
import com.flab.idolu.domain.member.exception.EmailDuplicateException;
import com.flab.idolu.domain.member.exception.MemberNotFoundException;
import com.flab.idolu.domain.member.exception.PasswordNotMatchException;
import com.flab.idolu.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Long signUp(SignUpMemberDto signUpMemberDto) {
		validateMemberDto(signUpMemberDto);
		if (isDuplicatedMember(signUpMemberDto)) {
			throw new EmailDuplicateException("이미 가입한 이메일입니다. 입력된 이메일: %s".formatted(signUpMemberDto.getEmail()));
		}

		Member member = signUpMemberDto.toEntity();
		member.setBcryptPassword(passwordEncoder.encode(member.getPassword()));
		return memberRepository.insertMember(member);
	}

	@Transactional(readOnly = true)
	public Member login(LoginMemberDto loginMemberDto) {
		validateLoginMemberDto(loginMemberDto);

		Member member = memberRepository.findByEmail(loginMemberDto.getEmail())
			.orElseThrow(() -> new MemberNotFoundException("존재하지 않는 이메일입니다."));
		if (!passwordEncoder.matches(loginMemberDto.getPassword(), member.getPassword())) {
			throw new PasswordNotMatchException("비밀번호가 틀렸습니다.");
		}

		return member;
	}

	@Transactional(readOnly = true)
	public MyInfoMemberDto getMemberInfo(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

		return MyInfoMemberDto.from(member);
	}

	@Transactional
	public void modifyMemberInfo(ModifyMemberDto modifyMemberDto, Long memberId) {
		validateModifyMemberDto(modifyMemberDto);

		memberRepository.updateMember(modifyMemberDto.toEntity(memberId));
	}

	@Transactional
	public void withdrawMember(Long memberId) {
		memberRepository.updateIsDeleted(memberId);
	}

	private void validateModifyMemberDto(ModifyMemberDto modifyMemberDto) {
		Assert.hasText(modifyMemberDto.getName(), "이름을 입력해야 합니다.");
		Assert.hasText(modifyMemberDto.getPhone(), "휴대전화를 입력해야 합니다.");

		validatePhone(modifyMemberDto.getPhone());
	}

	private void validateLoginMemberDto(LoginMemberDto loginMemberDto) {
		Assert.hasText(loginMemberDto.getEmail(), "이메일을 입력해야 합니다.");
		Assert.hasText(loginMemberDto.getPassword(), "비밀번호를 입력해야 합니다.");

		Assert.isTrue(
			Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
				loginMemberDto.getEmail()), "이메일 양식에 맞춰야 합니다.");
		Assert.isTrue(
			Pattern.matches("^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}", loginMemberDto.getPassword()),
			"비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다.");
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
				signUpMemberDto.getEmail()), "이메일 양식에 맞춰야 합니다.");
		Assert.isTrue(
			Pattern.matches("^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}", signUpMemberDto.getPassword()),
			"비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다.");
		Assert.isTrue(
			signUpMemberDto.getPassword().equals(signUpMemberDto.getPasswordConfirm()),
			"비밀번호와 비밀번호 확인이 일치해야 합니다.");
		validatePhone(signUpMemberDto.getPhone());
	}
}
