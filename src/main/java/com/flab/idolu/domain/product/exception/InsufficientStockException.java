package com.flab.idolu.domain.product.exception;

public class InsufficientStockException extends RuntimeException {

	public InsufficientStockException(String message) {
		super(message);
	}
}
