package com.flab.idolu.global.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.flab.idolu.global.annotation.SessionMemberId;
import com.flab.idolu.global.util.SessionManager;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SessionMemberIdResolver implements HandlerMethodArgumentResolver {

	private final SessionManager sessionManager;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(SessionMemberId.class) && Long.class.isAssignableFrom(
			parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		return sessionManager.getLoginMemberId();
	}
}
