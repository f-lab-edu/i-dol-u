package com.flab.idolu.domain.cart.dto.request;

import com.flab.idolu.domain.cart.entity.Cart;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartProductRequest {

	private Long productId;
	private Integer quantity;

	public Cart toEntity(Long memberId) {
		return Cart.builder()
			.productId(productId)
			.memberId(memberId)
			.quantity(quantity)
			.build();
	}
}
