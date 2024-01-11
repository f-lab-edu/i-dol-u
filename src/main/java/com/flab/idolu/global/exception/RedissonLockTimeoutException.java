package com.flab.idolu.global.exception;

public class RedissonLockTimeoutException extends RuntimeException {

	public RedissonLockTimeoutException(String message) {
		super(message);
	}
}
