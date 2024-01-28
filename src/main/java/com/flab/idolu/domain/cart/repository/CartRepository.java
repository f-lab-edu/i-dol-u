package com.flab.idolu.domain.cart.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.flab.idolu.domain.cart.entity.Cart;

@Mapper
@Repository
public interface CartRepository {

	void insertCart(Cart cart);
}
