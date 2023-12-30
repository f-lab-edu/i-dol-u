package com.flab.idolu.domain.product.service


import com.flab.idolu.domain.product.repository.ProductRepository
import spock.lang.Specification

class ProductServiceTest extends Specification {

    ProductService productService;
    ProductRepository productRepository = Mock();

    def setup() {
        productService = new ProductService(productRepository)
    }

    def "카테고리 및 아이돌별 상품 조회"() {
        given:
        productRepository.findByCategoryIdAndIDolId(_, _, _, _, _) >> List.of(_, _, _)
        productRepository.getTotalCountByCategoryIdAndIDolId(_, _) >> 3

        when:
        def productListResponseDto = productService.findByCategoryIdAndIDolId(1L, 1L, 1, 3, "DATE")

        then:
        productListResponseDto.products.size() == 3
        productListResponseDto.totalCount == 3
    }
}
