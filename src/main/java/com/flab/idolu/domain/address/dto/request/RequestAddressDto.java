package com.flab.idolu.domain.address.dto.request;

import com.flab.idolu.domain.address.entity.Address;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestAddressDto {

	private String recipient;
	private String phone;
	private String zipCode;
	private String address1;
	private String address2;

	public Address toEntity(Long memberId) {
		return Address.builder()
			.memberId(memberId)
			.recipient(recipient)
			.phone(phone)
			.zipCode(zipCode)
			.address1(address1)
			.address2(address2)
			.build();
	}
}
