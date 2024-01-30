package com.flab.idolu.domain.order.controller;

import static com.flab.idolu.global.common.ResponseMessage.Status.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping
	@MemberLoginCheck
	public ResponseEntity<ResponseMessage> selectOrders(
		@SessionMemberId Long memberId,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "0") int offset
	) {

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.result(orderService.findByMemberId(memberId, size, offset))
			.build());
	}

	@MemberLoginCheck
	@GetMapping("/{id}")
	public ResponseEntity<ResponseMessage> selectOrder(@PathVariable Long id, @SessionMemberId Long memberId) {

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.result(orderService.findById(id, memberId))
			.build());
	}
}
