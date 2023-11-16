package com.flab.idolu.global.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MDCLoggingFilter extends OncePerRequestFilter {

	private static String REQUEST_ID = "request_id";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		UUID uuid = UUID.randomUUID();
		MDC.put(REQUEST_ID, uuid.toString());
		filterChain.doFilter(request, response);
		MDC.clear();
	}
}
