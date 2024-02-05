package com.flab.idolu.domain.fixture;

import com.flab.idolu.domain.cart.dto.request.CartProductRequest;
import com.flab.idolu.domain.cart.dto.request.ModifyCartQuantityRequest;
import com.flab.idolu.domain.cart.dto.response.CartProductResponse;
import com.flab.idolu.domain.cart.entity.Cart;

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

	public static final CartProductRequest DEFAULT_CART_PRODUCT_REQUEST = CartProductRequest.builder()
		.productId(1L)
		.quantity(1)
		.build();

	public static final Cart DEFAULT_CART = Cart.builder()
		.id(1L)
		.productId(1L)
		.memberId(1L)
		.quantity(1)
		.build();

	public static final CartProductResponse DEFAULT_CART_PRODUCT_RESPONSE = CartProductResponse.builder()
		.id(1L)
		.productId(1L)
		.name("productA")
		.imageUrl("url")
		.quantity(1)
		.build();

	public static final ModifyCartQuantityRequest INSUFFICIENT_QUANTITY_CART_REQUEST = ModifyCartQuantityRequest.builder()
		.quantity(0)
		.build();

	public static final ModifyCartQuantityRequest INVALID_QUANTITY_CART_REQUEST = ModifyCartQuantityRequest.builder()
		.build();

	public static final ModifyCartQuantityRequest DEFAULT_CART_REQUEST = ModifyCartQuantityRequest.builder()
		.quantity(3)
		.build();
}
