package com.flab.idolu.domain.address.service


import com.flab.idolu.domain.address.repository.AddressRepository
import spock.lang.Specification

import static com.flab.idolu.domain.fixture.AddressFixture.*

class AddressServiceTest extends Specification {

    AddressService addressService;
    AddressRepository addressRepository = Mock()

    def setup() {
        addressService = new AddressService(addressRepository)
    }

    def "배송지 등록 성공 테스트"() {
        when:
        addressService.registerAddress(DEFAULT_REGISTER_ADDRESS, 1L)

        then:
        1 * addressRepository.insertAddress(_)
    }

    def "배송지 등록 실패 테스트: #exceptionMessage"() {
        when:
        addressService.registerAddress(addressDto, 1L)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.message == exceptionMessage;

        where:
        addressDto                       | exceptionMessage
        BLANK_RECIPIENT_REGISTER_ADDRESS | "수령자를 입력해야 합니다."
        BLANK_PHONE_REGISTER_ADDRESS     | "휴대전화를 입력해야 합니다."
        BLANK_ZIPCODE_REGISTER_ADDRESS   | "우편번호를 입력해야 합니다."
        BLANK_ADDRESS1_REGISTER_ADDRESS  | "기본주소를 입력해야 합니다."
        INVALID_PHONE_REGISTER_ADDRESS   | "휴대전화 양식에 맞춰야 합니다."
    }

    def "배송지 조회 테스트"() {
        given:
        addressRepository.findByMemberId(1L) >> List.of(_, _)

        when:
        def list = addressService.findByMemberId(1L)

        then:
        list.size() == 2
    }
}
