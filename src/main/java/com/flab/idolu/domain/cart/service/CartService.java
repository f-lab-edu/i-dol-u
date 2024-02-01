package com.flab.idolu.domain.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.flab.idolu.domain.cart.dto.request.CartProductRequest;
import com.flab.idolu.domain.cart.dto.response.CartProductResponse;
import com.flab.idolu.domain.cart.entity.Cart;
import com.flab.idolu.domain.cart.repository.CartRepository;
import com.flab.idolu.domain.product.exception.ProductNotFoundException;
import com.flab.idolu.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepository;
	private final ProductRepository productRepository;

	@Transactional
	public void insertCart(CartProductRequest cartProductRequest, Long memberId) {
		validateCartProduct(cartProductRequest);

		productRepository.findById(cartProductRequest.getProductId())
			.orElseThrow(() -> new ProductNotFoundException("상품이 없습니다."));

		Cart requestCart = cartProductRequest.toEntity(memberId);
		Optional<Cart> optionalCart = cartRepository.findByProductIdAndMemberId(requestCart.getProductId(), memberId);

		if (optionalCart.isPresent()) {
			cartRepository.updateCartQuantity(optionalCart.get().addQuantity(requestCart.getQuantity()));
		} else {
			cartRepository.insertCart(cartProductRequest.toEntity(memberId));
		}
	}

	@Transactional(readOnly = true)
	public List<CartProductResponse> findByMemberId(Long memberId) {
		return cartRepository.findByMemberId(memberId);
	}

	private void validateCartProduct(CartProductRequest cartProductRequest) {
		Assert.notNull(cartProductRequest.getProductId(), "상품 id를 입력해주세요.");
		Assert.notNull(cartProductRequest.getQuantity(), "수량을 입력해주세요.");
		Assert.isTrue(cartProductRequest.getQuantity().compareTo(0) > 0, "수량이 0보다 커야 합니다.");
	}
}
