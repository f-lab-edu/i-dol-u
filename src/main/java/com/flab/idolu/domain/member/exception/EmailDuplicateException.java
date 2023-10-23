package com.flab.idolu.domain.member.exception;

public class EmailDuplicateException extends RuntimeException {

	public EmailDuplicateException(String message) {
		super(message);
	}
}
