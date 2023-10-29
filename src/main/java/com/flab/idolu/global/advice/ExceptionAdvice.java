package com.flab.idolu.global.advice;

import static com.flab.idolu.global.common.ResponseMessage.Status.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flab.idolu.domain.member.exception.EmailDuplicateException;
import com.flab.idolu.domain.member.exception.MemberNotFoundException;
import com.flab.idolu.domain.member.exception.PasswordNotMatchException;
import com.flab.idolu.global.common.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<ResponseMessage> illegalArgumentException(IllegalArgumentException exception) {
		return ResponseEntity.status(BAD_REQUEST)
			.body(ResponseMessage.builder()
				.status(FAIL)
				.message(exception.getMessage())
				.build());
	}

	@ExceptionHandler(EmailDuplicateException.class)
	protected ResponseEntity<ResponseMessage> emailDuplicationException(EmailDuplicateException exception) {
		return ResponseEntity.status(BAD_REQUEST)
			.body(ResponseMessage.builder()
				.status(FAIL)
				.message("이미 가입한 이메일입니다.")
				.build());
	}

	@ExceptionHandler(MemberNotFoundException.class)
	protected ResponseEntity<ResponseMessage> memberNotFoundException(MemberNotFoundException exception) {
		return ResponseEntity.badRequest()
			.body(ResponseMessage.builder()
				.message(exception.getMessage())
				.build());
	}

	@ExceptionHandler(PasswordNotMatchException.class)
	protected ResponseEntity<ResponseMessage> memberNotFoundException(PasswordNotMatchException exception) {
		return ResponseEntity.badRequest()
			.body(ResponseMessage.builder()
				.message(exception.getMessage())
				.build());
	}
}
