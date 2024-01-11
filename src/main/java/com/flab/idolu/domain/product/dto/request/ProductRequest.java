package com.flab.idolu.domain.product.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductRequest {

	private Long productId;
	private Integer quantity;
}
