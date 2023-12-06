package com.flab.idolu.global.util;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SessionManager {

	private static final String LOGIN_MEMBER_ID = "LOGIN_MEMBER_ID";
	private final HttpSession session;

	public Long getLoginMemberId() {
		return (Long) session.getAttribute(LOGIN_MEMBER_ID);
	}

	public void setLoginMemberId(Long id) {
		session.setAttribute(LOGIN_MEMBER_ID, id);
	}

	public void removeLoginMemberId() {
		session.removeAttribute(LOGIN_MEMBER_ID);
	}
}
