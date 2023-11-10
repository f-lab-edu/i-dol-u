package com.flab.idolu.domain.address.service;

import static com.flab.idolu.global.util.ValidateDtoUtil.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.flab.idolu.domain.address.dto.request.RequestAddressDto;
import com.flab.idolu.domain.address.dto.response.AddressInfoDto;
import com.flab.idolu.domain.address.exception.AddressNotFoundException;
import com.flab.idolu.domain.address.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

	private final AddressRepository addressRepository;

	@Transactional
	public void registerAddress(RequestAddressDto requestAddressDto, Long memberId) {
		validateRegisterAddressDto(requestAddressDto);

		addressRepository.insertAddress(requestAddressDto.toEntity(memberId));
	}

	@Transactional(readOnly = true)
	public List<AddressInfoDto> findByMemberId(Long memberId) {
		return addressRepository.findByMemberId(memberId);
	}

	@Transactional(readOnly = true)
	public AddressInfoDto findByIdAndMemberId(Long id, Long memberId) {
		return addressRepository.findByIdAndMemberId(id, memberId)
			.orElseThrow(() -> new AddressNotFoundException("조회하려는 배송지가 없습니다."));
	}

	@Transactional
	public void updateAddressByIdAndMemberId(RequestAddressDto requestAddressDto, Long id, Long memberId) {
		validateRegisterAddressDto(requestAddressDto);

		addressRepository.updateAddressByIdAndMemberId(requestAddressDto.toEntity(memberId), id);
	}

	@Transactional
	public void updateIsDeletedByIdAndMemberId(Long id, Long memberId) {
		addressRepository.updateIsDeletedByIdAndMemberId(id, memberId);
	}

	private void validateRegisterAddressDto(RequestAddressDto requestAddressDto) {
		Assert.hasText(requestAddressDto.getRecipient(), "수령자를 입력해야 합니다.");
		Assert.hasText(requestAddressDto.getPhone(), "휴대전화를 입력해야 합니다.");
		Assert.hasText(requestAddressDto.getZipCode(), "우편번호를 입력해야 합니다.");
		Assert.hasText(requestAddressDto.getAddress1(), "기본주소를 입력해야 합니다.");

		validatePhone(requestAddressDto.getPhone());
	}
}
