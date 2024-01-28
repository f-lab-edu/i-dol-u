package com.flab.idolu.domain.cart.entity;

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
public class Cart {

	private Long id;
	private Long productId;
	private Long memberId;
	private Integer quantity;
	private boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Cart addQuantity(Integer quantity) {
		this.quantity += quantity;
		return this;
	}
}
