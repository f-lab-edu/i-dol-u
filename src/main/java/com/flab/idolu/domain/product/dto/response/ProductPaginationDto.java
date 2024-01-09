package com.flab.idolu.domain.product.dto.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductPaginationDto {

	private Long id;
	private Long categoryId;
	private Long iDolId;
	private String name;
	private BigDecimal price;
	private Integer stock;
	private String imageUrl;
}
