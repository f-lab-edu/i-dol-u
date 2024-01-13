package com.flab.idolu.global.util;

import java.util.regex.Pattern;

import org.springframework.util.Assert;

public class ValidateDtoUtil {

	private ValidateDtoUtil() {
	}

	public static void validateRecipient(String recipient) {
		Assert.hasText(recipient, "수령자를 입력해야 합니다.");
	}

	public static void validateZipCode(String zipCode) {
		Assert.hasText(zipCode, "우편번호를 입력해야 합니다.");
	}

	public static void validateAddress1(String address1) {
		Assert.hasText(address1, "기본주소를 입력해야 합니다.");
	}

	public static void validatePhone(String phone) {
		Assert.hasText(phone, "휴대전화를 입력해야 합니다.");
		Assert.isTrue(
			Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", phone),
			"휴대전화 양식에 맞춰야 합니다.");
	}
}
