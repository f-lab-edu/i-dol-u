package com.flab.idolu.domain.cart.dto.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartProductResponse {

	private Long id;
	private Long productId;
	private String name;
	private int quantity;
	private BigDecimal price;
	private String imageUrl;
}

