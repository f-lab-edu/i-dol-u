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
public class OrderProduct {

	private Long id;
	private Long orderId;
	private Long productId;
	private BigDecimal price;
	private Integer quantity;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
