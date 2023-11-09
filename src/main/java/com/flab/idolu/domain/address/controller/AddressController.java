package com.flab.idolu.domain.address.controller;

import static com.flab.idolu.global.common.ResponseMessage.Status.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.idolu.domain.address.dto.request.RegisterAddressDto;
import com.flab.idolu.domain.address.service.AddressService;
import com.flab.idolu.global.annotation.MemberLoginCheck;
import com.flab.idolu.global.common.ResponseMessage;
import com.flab.idolu.global.util.SessionUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

	private final AddressService addressService;

	@PostMapping
	@MemberLoginCheck
	public ResponseEntity<ResponseMessage> registerAddress(
		@RequestBody RegisterAddressDto registerAddressDto,
		HttpSession httpSession) {

		Long memberId = SessionUtil.getLoginMemberId(httpSession);
		addressService.registerAddress(registerAddressDto, memberId);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}

	@GetMapping
	@MemberLoginCheck
	public ResponseEntity<ResponseMessage> getMyAddress(HttpSession httpSession) {
		Long memberId = SessionUtil.getLoginMemberId(httpSession);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.result(addressService.findByMemberId(memberId))
			.build());
	}
}
