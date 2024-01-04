package com.flab.idolu.domain.order.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
	private Integer totalQuantity;
	private boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
