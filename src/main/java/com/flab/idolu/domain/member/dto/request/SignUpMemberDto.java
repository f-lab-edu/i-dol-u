package com.flab.idolu.domain.member.dto.request;

import com.flab.idolu.domain.member.entity.Member;
import com.flab.idolu.domain.member.entity.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpMemberDto {

	private String email;
	private String password;
	private String passwordConfirm;
	private String name;
	private String phone;
	private String role;

	public Member toEntity() {
		return Member.builder()
			.email(email)
			.password(password)
			.name(name)
			.phone(phone)
			.role(Role.valueOf(role))
			.build();
	}
}
