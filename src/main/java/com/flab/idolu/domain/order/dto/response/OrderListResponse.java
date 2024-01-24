package com.flab.idolu.domain.order.dto.response;

import java.math.BigDecimal;

import com.flab.idolu.domain.order.entity.OrderStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderListResponse {

	private Long id;
	private BigDecimal totalPrice;
	private int productCount;
	private String name;
	private OrderStatus orderStatus;
}
