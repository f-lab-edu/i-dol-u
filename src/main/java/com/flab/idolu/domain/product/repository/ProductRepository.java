package com.flab.idolu.domain.product.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.flab.idolu.domain.product.dto.response.ProductListResponseDto;

@Mapper
@Repository
public interface ProductRepository {

	List<ProductListResponseDto> findByCategoryIdAndIDolId(
		@Param("categoryId") Integer categoryId,
		@Param("iDolId") Integer iDolId,
		@Param("offset") int offset,
		@Param("size") int size,
		@Param("order") String order);
}
