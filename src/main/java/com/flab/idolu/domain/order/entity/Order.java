package com.flab.idolu.domain.order.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

	private Long id;
	private Long memberId;
	private String recipient;
	private String phone;
	private String zipCode;
	private String address1;
	private String address2;
	private OrderStatus orderStatus;
	private BigDecimal totalPrice;
	private Integer totalStock;
	private boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
