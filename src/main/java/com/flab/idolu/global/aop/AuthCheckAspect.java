package com.flab.idolu.global.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.flab.idolu.domain.member.exception.UnauthorizedMemberException;
import com.flab.idolu.global.util.SessionUtil;

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class AuthCheckAspect {

	@Before("@annotation(com.flab.idolu.global.annotation.MemberLoginCheck)")
	public void memberLoginCheck(JoinPoint joinPoint) {

		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest()
			.getSession();

		Long memberId = SessionUtil.getLoginMemberId(session);
		if (memberId == null) {
			throw new UnauthorizedMemberException("로그인이 필요합니다.");
		}
	}
}
