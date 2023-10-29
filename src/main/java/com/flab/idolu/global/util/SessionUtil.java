package com.flab.idolu.global.util;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {

	private static final String LOGIN_MEMBER_ID = "LOGIN_MEMBER_ID";

	private SessionUtil() {
	}

	public static void setLoginMemberId(HttpSession session, String id) {
		session.setAttribute(LOGIN_MEMBER_ID, id);
	}

	public static String getLoginMemberId(HttpSession session) {
		return (String)session.getAttribute(LOGIN_MEMBER_ID);
	}
}
