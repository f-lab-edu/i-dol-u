package com.flab.idolu.global.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.flab.idolu.domain.member.exception.UnauthorizedMemberException;
import com.flab.idolu.global.util.SessionManager;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthCheckAspect {

	private final SessionManager sessionManager;

	@Before("@annotation(com.flab.idolu.global.annotation.MemberLoginCheck)")
	public void memberLoginCheck(JoinPoint joinPoint) {

		Long memberId = sessionManager.getLoginMemberId();
		if (memberId == null) {
			throw new UnauthorizedMemberException("로그인이 필요합니다.");
		}
	}
}
