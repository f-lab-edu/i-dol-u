package com.flab.idolu.global.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseMessage {

	private final Status status;
	private final String message;
	private final Object result;

	public enum Status {
		SUCCESS, FAIL
	}
}
