package com.flab.idolu.domain.product.controller;

import static com.flab.idolu.global.common.ResponseMessage.Status.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flab.idolu.domain.product.dto.request.ProductRequest;
import com.flab.idolu.domain.product.service.ProductService;
import com.flab.idolu.global.common.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<ResponseMessage> getProductsByCategoryAndIDol(
		@RequestParam(required = false) Long categoryId,
		@RequestParam(required = false) Long iDolId,
		@RequestParam(defaultValue = "0") int offset,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "DATE") String order
	) {

		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.result(productService.findByCategoryIdAndIDolId(categoryId, iDolId, offset, size, order))
			.build());
	}

	@PostMapping("/pessimisticLock")
	public ResponseEntity<ResponseMessage> decreaseStockWithPessimisticLock(
		@RequestBody ProductRequest productRequest) {

		productService.decreaseStockWithPessimisticLock(productRequest.getProductId(), productRequest.getQuantity());
		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}

	@PostMapping("/distributedLock")
	public ResponseEntity<ResponseMessage> decreaseStockWithDistributedLock(
		@RequestBody ProductRequest productRequest) {

		productService.decreaseStockWithDistributedLock(productRequest.getProductId(), productRequest.getQuantity());
		return ResponseEntity.ok(ResponseMessage.builder()
			.status(SUCCESS)
			.build());
	}
}
