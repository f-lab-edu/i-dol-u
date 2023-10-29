package com.flab.idolu.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginMemberDto {

	private String email;
	private String password;
}
