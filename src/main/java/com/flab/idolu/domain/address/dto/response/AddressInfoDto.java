package com.flab.idolu.domain.address.dto.response;

import lombok.Getter;

@Getter
public class AddressInfoDto {

	private Long id;
	private String recipient;
	private String phone;
	private String zipCode;
	private String address1;
	private String address2;

}
