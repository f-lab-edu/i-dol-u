package com.flab.idolu.domain.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.flab.idolu.domain.cart.dto.request.CartProductRequest;
import com.flab.idolu.domain.cart.dto.request.ModifyCartQuantityRequest;
import com.flab.idolu.domain.cart.dto.response.CartProductResponse;
import com.flab.idolu.domain.cart.entity.Cart;
import com.flab.idolu.domain.cart.exception.CartNotFoundException;
import com.flab.idolu.domain.cart.repository.CartRepository;
import com.flab.idolu.domain.member.exception.UnauthorizedMemberException;
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

	@Transactional
	public void updateCartQuantity(Long cartId, ModifyCartQuantityRequest modifyCartQuantityRequest, Long memberId) {
		validateCartQuantityRequest(modifyCartQuantityRequest);

		Cart cart = cartRepository.findById(cartId)
			.orElseThrow(() -> new CartNotFoundException("존재하는 카트 상품이 없습니다."));
		if (!cart.getMemberId().equals(memberId)) {
			throw new UnauthorizedMemberException("본인의 카트 상품만 수량 수정이 가능합니다.");
		}

		cart.changQuantity(modifyCartQuantityRequest.quantity());
		cartRepository.updateCartQuantity(cart);
	}

	private void validateCartQuantityRequest(ModifyCartQuantityRequest modifyCartQuantityRequest) {
		Assert.notNull(modifyCartQuantityRequest.quantity(), "수량을 입력해주세요.");
		Assert.isTrue(modifyCartQuantityRequest.quantity().compareTo(0) > 0, "수량이 0보다 커야 합니다.");
	}

	private void validateCartProduct(CartProductRequest cartProductRequest) {
		Assert.notNull(cartProductRequest.getProductId(), "상품 id를 입력해주세요.");
		Assert.notNull(cartProductRequest.getQuantity(), "수량을 입력해주세요.");
		Assert.isTrue(cartProductRequest.getQuantity().compareTo(0) > 0, "수량이 0보다 커야 합니다.");
	}
}
