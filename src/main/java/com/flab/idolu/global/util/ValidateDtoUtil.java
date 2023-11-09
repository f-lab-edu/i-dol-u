package com.flab.idolu.global.util;

import java.util.regex.Pattern;

import org.springframework.util.Assert;

public class ValidateDtoUtil {

	private ValidateDtoUtil() {
	}

	public static void validatePhone(String phone) {
		Assert.isTrue(
			Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", phone),
			"휴대전화 양식에 맞춰야 합니다.");
	}
}
