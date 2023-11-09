package com.flab.idolu.domain.address.service;

import static com.flab.idolu.global.util.ValidateDtoUtil.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.flab.idolu.domain.address.dto.request.RegisterAddressDto;
import com.flab.idolu.domain.address.dto.response.AddressInfoDto;
import com.flab.idolu.domain.address.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

	private final AddressRepository addressRepository;

	@Transactional
	public void registerAddress(RegisterAddressDto registerAddressDto, Long memberId) {
		validateRegisterAddressDto(registerAddressDto);

		addressRepository.insertAddress(registerAddressDto.toEntity(memberId));
	}

	@Transactional(readOnly = true)
	public List<AddressInfoDto> findByMemberId(Long memberId) {

		return addressRepository.findByMemberId(memberId);
	}

	private void validateRegisterAddressDto(RegisterAddressDto registerAddressDto) {
		Assert.hasText(registerAddressDto.getRecipient(), "수령자를 입력해야 합니다.");
		Assert.hasText(registerAddressDto.getPhone(), "휴대전화를 입력해야 합니다.");
		Assert.hasText(registerAddressDto.getZipCode(), "우편번호를 입력해야 합니다.");
		Assert.hasText(registerAddressDto.getAddress1(), "기본주소를 입력해야 합니다.");

		validatePhone(registerAddressDto.getPhone());
	}
}
