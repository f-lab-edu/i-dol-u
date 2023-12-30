package com.flab.idolu.domain.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flab.idolu.domain.product.dto.response.ProductListResponseDto;
import com.flab.idolu.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public List<ProductListResponseDto> findByCategoryIdAndIDolId(Integer categoryId, Integer iDolId,
		int offset, int size, String order) {

		return productRepository.findByCategoryIdAndIDolId(categoryId, iDolId, offset * size, size, order);
	}
}
