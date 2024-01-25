package com.flab.idolu.domain.order.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDto {

	private Long productId;
	private String name;
	private BigDecimal price;
	private Integer quantity;
	private String imageUrl;
}
