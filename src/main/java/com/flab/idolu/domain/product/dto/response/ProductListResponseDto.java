package com.flab.idolu.domain.product.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductListResponseDto {

	private List<ProductPaginationDto> products;
	private Long totalCount;
}
