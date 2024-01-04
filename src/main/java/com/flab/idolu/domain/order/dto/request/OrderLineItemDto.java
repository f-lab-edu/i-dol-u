package com.flab.idolu.domain.order.dto.request;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderLineItemDto {

	private Long productId;
	private BigDecimal price;
	private Integer quantity;
}
