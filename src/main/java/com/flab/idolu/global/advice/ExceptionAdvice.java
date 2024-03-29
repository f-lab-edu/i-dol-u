package com.flab.idolu.global.advice;

import static com.flab.idolu.global.common.ResponseMessage.Status.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flab.idolu.domain.address.exception.AddressNotFoundException;
import com.flab.idolu.domain.member.exception.EmailDuplicateException;
import com.flab.idolu.domain.member.exception.MemberNotFoundException;
import com.flab.idolu.domain.member.exception.PasswordNotMatchException;
import com.flab.idolu.domain.member.exception.UnauthorizedMemberException;
import com.flab.idolu.domain.order.exception.InvalidOrderOwnerException;
import com.flab.idolu.domain.product.exception.InsufficientStockException;
import com.flab.idolu.domain.product.exception.ProductNotFoundException;
import com.flab.idolu.global.common.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<ResponseMessage> illegalArgumentException(IllegalArgumentException exception) {
		log.info("IllegalArgumentException: {}", exception.getMessage());
		return ResponseEntity.status(BAD_REQUEST)
			.body(ResponseMessage.builder()
				.status(FAIL)
				.message(exception.getMessage())
				.build());
	}

	@ExceptionHandler(EmailDuplicateException.class)
	protected ResponseEntity<ResponseMessage> emailDuplicationException(EmailDuplicateException exception) {
		log.info("EmailDuplicateException: {}", exception.getMessage());
		return ResponseEntity.status(BAD_REQUEST)
			.body(ResponseMessage.builder()
				.status(FAIL)
				.message("이미 가입한 이메일입니다.")
				.build());
	}

	@ExceptionHandler(MemberNotFoundException.class)
	protected ResponseEntity<ResponseMessage> memberNotFoundException(MemberNotFoundException exception) {
		log.info("MemberNotFoundException: {}", exception.getMessage());
		return ResponseEntity.badRequest()
			.body(ResponseMessage.builder()
				.status(FAIL)
				.message(exception.getMessage())
				.build());
	}

	@ExceptionHandler(PasswordNotMatchException.class)
	protected ResponseEntity<ResponseMessage> memberNotFoundException(PasswordNotMatchException exception) {
		log.info("PasswordNotMatchException: {}", exception.getMessage());
		return ResponseEntity.badRequest()
			.body(ResponseMessage.builder()
				.status(FAIL)
				.message(exception.getMessage())
				.build());
	}

	@ExceptionHandler(UnauthorizedMemberException.class)
	protected ResponseEntity<ResponseMessage> unauthorizedMemberException(UnauthorizedMemberException exception) {
		log.info("UnauthorizedMemberException: {}", exception.getMessage());
		return ResponseEntity.status(UNAUTHORIZED)
			.body(ResponseMessage.builder()
				.status(FAIL)
				.message(exception.getMessage())
				.build());
	}

	@ExceptionHandler(AddressNotFoundException.class)
	protected ResponseEntity<ResponseMessage> addressNotFoundException(AddressNotFoundException exception) {
		log.info("AddressNotFoundException: {}", exception.getMessage());
		return ResponseEntity.status(BAD_REQUEST)
			.body(ResponseMessage.builder()
				.status(FAIL)
				.message(exception.getMessage())
				.build());
	}

	@ExceptionHandler(ProductNotFoundException.class)
	protected ResponseEntity<ResponseMessage> productNotFoundException(ProductNotFoundException exception) {
		log.info("AddressNotFoundException: {}", exception.getMessage());
		return ResponseEntity.status(BAD_REQUEST)
			.body(ResponseMessage.builder()
				.status(FAIL)
				.message(exception.getMessage())
				.build());
	}

	@ExceptionHandler(InsufficientStockException.class)
	protected ResponseEntity<ResponseMessage> insufficientStockException(InsufficientStockException exception) {
		log.info("AddressNotFoundException: {}", exception.getMessage());
		return ResponseEntity.status(BAD_REQUEST)
			.body(ResponseMessage.builder()
				.status(FAIL)
				.message(exception.getMessage())
				.build());
	}

	@ExceptionHandler(InvalidOrderOwnerException.class)
	protected ResponseEntity<ResponseMessage> invalidOrderOwnerException(InvalidOrderOwnerException exception) {
		log.info("InvalidOrderOwnerException: {}", exception.getMessage());
		return ResponseEntity.status(FORBIDDEN)
			.body(ResponseMessage.builder()
				.status(FAIL)
				.message(exception.getMessage())
				.build());
	}
}
