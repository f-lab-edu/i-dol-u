package com.flab.idolu.domain.member.exception;

public class PasswordNotMatchException extends RuntimeException {

	public PasswordNotMatchException(String message) {
		super(message);
	}
}
