package com.flab.idolu.domain.cart.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.flab.idolu.domain.cart.dto.response.CartProductResponse;
import com.flab.idolu.domain.cart.entity.Cart;

@Mapper
@Repository
public interface CartRepository {

	void insertCart(Cart cart);

	Optional<Cart> findByProductIdAndMemberId(@Param("productId") Long productId, @Param("memberId") Long memberId);

	void updateCartQuantity(Cart cart);

	List<CartProductResponse> findByMemberId(Long memberId);
}
