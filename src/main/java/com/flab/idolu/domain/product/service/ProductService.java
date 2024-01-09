package com.flab.idolu.domain.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.idolu.domain.product.dto.response.ProductListResponseDto;
import com.flab.idolu.domain.product.dto.response.ProductPaginationDto;
import com.flab.idolu.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	@Transactional(readOnly = true)
	public ProductListResponseDto findByCategoryIdAndIDolId(Long categoryId, Long iDolId,
		int offset, int size, String order) {

		List<ProductPaginationDto> products = productRepository.findByCategoryIdAndIDolId(categoryId,
			iDolId, offset * size, size, order);
		Long totalCount = productRepository.getTotalCountByCategoryIdAndIDolId(categoryId, iDolId);

		return ProductListResponseDto.builder()
			.products(products)
			.totalCount(totalCount)
			.build();
	}
}
