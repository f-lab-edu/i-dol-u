package com.flab.idolu.global.common;

import lombok.Getter;

@Getter
public class ResponseMessage {

	private final Status status;
	private final String message;
	private final Object result;

	private ResponseMessage(Builder builder) {
		this.status = builder.status;
		this.message = builder.message;
		this.result = builder.result;
	}

	/**
	 * Builder 패턴적용 - Status는 필수값 나머지는 선택
	 */
	public static class Builder {
		private final Status status;
		private String message = null;
		private Object result = null;

		public Builder(Status status) {
			this.status = status;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder result(String result) {
			this.result = result;
			return this;
		}

		public ResponseMessage build() {
			return new ResponseMessage(this);
		}
	}

	public enum Status {
		SUCCESS, FAIL
	}
}
