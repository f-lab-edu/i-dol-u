package com.flab.idolu.domain.fixture;

import com.flab.idolu.domain.address.dto.request.RequestAddressDto;
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

	public static final RequestAddressDto DEFAULT_REQUEST_ADDRESS = RequestAddressDto.builder()
		.recipient("oneny")
		.phone("01011111111")
		.zipCode("51324")
		.address1("기본 주소")
		.address2("상세 주소")
		.build();

	public static final RequestAddressDto BLANK_RECIPIENT_REGISTER_ADDRESS = RequestAddressDto.builder()
		.recipient("")
		.phone("01011111111")
		.zipCode("51324")
		.address1("기본 주소")
		.address2("상세 주소")
		.build();

	public static final RequestAddressDto BLANK_PHONE_REGISTER_ADDRESS = RequestAddressDto.builder()
		.recipient("oneny")
		.phone("")
		.zipCode("51324")
		.address1("기본 주소")
		.address2("상세 주소")
		.build();

	public static final RequestAddressDto BLANK_ZIPCODE_REGISTER_ADDRESS = RequestAddressDto.builder()
		.recipient("oneny")
		.phone("01011111111")
		.zipCode("")
		.address1("기본 주소")
		.address2("상세 주소")
		.build();

	public static final RequestAddressDto BLANK_ADDRESS1_REGISTER_ADDRESS = RequestAddressDto.builder()
		.recipient("oneny")
		.phone("01011111111")
		.zipCode("51324")
		.address1("")
		.address2("상세 주소")
		.build();

	public static final RequestAddressDto INVALID_PHONE_REGISTER_ADDRESS = RequestAddressDto.builder()
		.recipient("oneny")
		.phone("1011111111")
		.zipCode("51324")
		.address1("기본 주소")
		.address2("상세 주소")
		.build();
}
