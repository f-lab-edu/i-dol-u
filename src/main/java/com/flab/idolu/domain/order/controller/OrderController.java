package com.flab.idolu.domain.order.controller;

import static com.flab.idolu.global.common.ResponseMessage.Status.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.idolu.domain.order.dto.request.OrderRequest;
import com.flab.idolu.domain.order.service.OrderService;
import com.flab.idolu.global.annotation.MemberLoginCheck;
import com.flab.idolu.global.annotation.SessionMemberId;
import com.flab.idolu.global.common.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	@MemberLoginCheck
	public ResponseEntity<ResponseMessage> placeOrder(
		@RequestBody OrderRequest orderRequest,
		@SessionMemberId Long memberId) {

		orderService.placeOrder(orderRequest, memberId);

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}
}
