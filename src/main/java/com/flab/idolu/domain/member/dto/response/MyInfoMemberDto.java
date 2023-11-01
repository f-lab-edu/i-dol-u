package com.flab.idolu.domain.member.dto.response;

import com.flab.idolu.domain.member.entity.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyInfoMemberDto {

	private String email;
	private String name;
	private String phone;

	public static MyInfoMemberDto from(Member member) {
		return MyInfoMemberDto.builder()
			.email(member.getEmail())
			.name(member.getName())
			.phone(member.getPhone())
			.build();
	}
}
