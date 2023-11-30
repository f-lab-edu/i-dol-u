package com.flab.idolu.domain.address.controller;

import static com.flab.idolu.global.common.ResponseMessage.Status.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.idolu.domain.address.dto.request.RequestAddressDto;
import com.flab.idolu.domain.address.service.AddressService;
import com.flab.idolu.global.annotation.MemberLoginCheck;
import com.flab.idolu.global.common.ResponseMessage;
import com.flab.idolu.global.util.SessionManager;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

	private final AddressService addressService;
	private final SessionManager sessionManager;

	@PostMapping
	@MemberLoginCheck
	public ResponseEntity<ResponseMessage> registerAddress(@RequestBody RequestAddressDto requestAddressDto) {

		Long memberId = sessionManager.getLoginMemberId();
		addressService.registerAddress(requestAddressDto, memberId);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}

	@GetMapping
	@MemberLoginCheck
	public ResponseEntity<ResponseMessage> getMyAddresses() {
		Long memberId = sessionManager.getLoginMemberId();

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.result(addressService.findByMemberId(memberId))
			.build());
	}

	@GetMapping("/{id}")
	@MemberLoginCheck
	public ResponseEntity<ResponseMessage> getMyAddress(@PathVariable Long id) {
		Long memberId = sessionManager.getLoginMemberId();

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.result(addressService.findByIdAndMemberId(id, memberId))
			.build());
	}

	@PutMapping("/{id}")
	@MemberLoginCheck
	public ResponseEntity<ResponseMessage> modifyMyAddress(
		@PathVariable Long id,
		@RequestBody RequestAddressDto requestAddressDto) {

		Long memberId = sessionManager.getLoginMemberId();
		addressService.updateAddressByIdAndMemberId(requestAddressDto, id, memberId);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}

	@PatchMapping("/{id}")
	@MemberLoginCheck
	public ResponseEntity<ResponseMessage> deleteAddress(@PathVariable Long id) {
		Long memberId = sessionManager.getLoginMemberId();
		addressService.updateIsDeletedByIdAndMemberId(id, memberId);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}
}
