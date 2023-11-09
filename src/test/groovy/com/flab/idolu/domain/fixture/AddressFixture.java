package com.flab.idolu.domain.fixture;

import com.flab.idolu.domain.address.dto.request.RegisterAddressDto;
import com.flab.idolu.domain.address.entity.Address;

public class AddressFixture {

	public static final Address DEFAULT_ADDRESS = Address.builder()
		.memberId(1L)
		.recipient("oneny")
		.phone("01011111111")
		.zipCode("51324")
		.address1("기본 주소")
		.address2("상세 주소")
		.build();

	public static final RegisterAddressDto DEFAULT_REGISTER_ADDRESS = RegisterAddressDto.builder()
		.recipient("oneny")
		.phone("01011111111")
		.zipCode("51324")
		.address1("기본 주소")
		.address2("상세 주소")
		.build();

	public static final RegisterAddressDto BLANK_RECIPIENT_REGISTER_ADDRESS = RegisterAddressDto.builder()
		.recipient("")
		.phone("01011111111")
		.zipCode("51324")
		.address1("기본 주소")
		.address2("상세 주소")
		.build();

	public static final RegisterAddressDto BLANK_PHONE_REGISTER_ADDRESS = RegisterAddressDto.builder()
		.recipient("oneny")
		.phone("")
		.zipCode("51324")
		.address1("기본 주소")
		.address2("상세 주소")
		.build();

	public static final RegisterAddressDto BLANK_ZIPCODE_REGISTER_ADDRESS = RegisterAddressDto.builder()
		.recipient("oneny")
		.phone("01011111111")
		.zipCode("")
		.address1("기본 주소")
		.address2("상세 주소")
		.build();

	public static final RegisterAddressDto BLANK_ADDRESS1_REGISTER_ADDRESS = RegisterAddressDto.builder()
		.recipient("oneny")
		.phone("01011111111")
		.zipCode("51324")
		.address1("")
		.address2("상세 주소")
		.build();

	public static final RegisterAddressDto INVALID_PHONE_REGISTER_ADDRESS = RegisterAddressDto.builder()
		.recipient("oneny")
		.phone("1011111111")
		.zipCode("51324")
		.address1("기본 주소")
		.address2("상세 주소")
		.build();
}
