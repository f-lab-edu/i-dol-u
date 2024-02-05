package com.flab.idolu.domain.cart.controller;

import static com.flab.idolu.global.common.ResponseMessage.Status.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flab.idolu.domain.cart.dto.request.CartProductRequest;
import com.flab.idolu.domain.cart.dto.request.ModifyCartQuantityRequest;
import com.flab.idolu.domain.cart.service.CartService;
import com.flab.idolu.global.annotation.MemberLoginCheck;
import com.flab.idolu.global.annotation.SessionMemberId;
import com.flab.idolu.global.common.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

	private final CartService cartService;

	@PostMapping
	@MemberLoginCheck
	public ResponseEntity<ResponseMessage> createCartProduct(
		@RequestBody CartProductRequest cartProductRequest,
		@SessionMemberId Long memberId) {
		
		cartService.insertCart(cartProductRequest, memberId);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}

	@GetMapping
	@MemberLoginCheck
	public ResponseEntity<ResponseMessage> getMyCartProduct(@SessionMemberId Long memberId) {

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.result(cartService.findByMemberId(memberId))
			.build());
	}

	@MemberLoginCheck
	@PutMapping("/{cartId}")
	public ResponseEntity<ResponseMessage> updateCartQuantity(
		@SessionMemberId Long memberId,
		@PathVariable Long cartId,
		@RequestBody ModifyCartQuantityRequest modifyCartQuantityRequest
		) {
		cartService.updateCartQuantity(cartId, modifyCartQuantityRequest, memberId);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}
}
