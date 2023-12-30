package com.flab.idolu.domain.product.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.flab.idolu.domain.product.dto.response.ProductPaginationDto;
import com.flab.idolu.domain.product.entity.Product;

@Mapper
@Repository
public interface ProductRepository {

	List<ProductPaginationDto> findByCategoryIdAndIDolId(
		@Param("categoryId") Long categoryId,
		@Param("iDolId") Long iDolId,
		@Param("offset") int offset,
		@Param("size") int size,
		@Param("order") String order);

	Long getTotalCountByCategoryIdAndIDolId(@Param("categoryId") Long categoryId, @Param("iDolId") Long iDolId);

	Optional<Product> findById(Long id);

	void updateProductStock(Product product);
}
