package com.flab.idolu.domain.order.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.flab.idolu.domain.order.entity.Order;
import com.flab.idolu.domain.order.entity.OrderProduct;

@Mapper
@Repository
public interface OrderRepository {

	void insertOrder(Order order);

	void insertOrderProduct(List<OrderProduct> orderProduct);
}
