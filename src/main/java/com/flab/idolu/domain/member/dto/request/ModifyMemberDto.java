package com.flab.idolu.domain.member.dto.request;

import com.flab.idolu.domain.member.entity.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModifyMemberDto {

	private String name;
	private String phone;

	public Member toEntity(Long memberId) {
		return Member.builder()
			.id(memberId)
			.name(name)
			.phone(phone)
			.build();
	}
}
