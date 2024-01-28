package com.flab.idolu.domain.fixture;

import com.flab.idolu.domain.cart.dto.request.CartProductRequest;

public class CartFixture {

	public static final CartProductRequest INVALID_PRODUCT_ID_CART = CartProductRequest.builder()
		.quantity(10)
		.build();

	public static final CartProductRequest INVALID_QUANTITY_CART = CartProductRequest.builder()
		.productId(1L)
		.build();

	public static final CartProductRequest INSUFFICIENT_QUANTITY_CART = CartProductRequest.builder()
		.productId(1L)
		.quantity(0)
		.build();

	public static final CartProductRequest DEFAULT_CART = CartProductRequest.builder()
		.productId(1L)
		.quantity(1)
		.build();
}
