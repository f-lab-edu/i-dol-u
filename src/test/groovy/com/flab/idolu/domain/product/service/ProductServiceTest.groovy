package com.flab.idolu.domain.product.service

import com.flab.idolu.domain.product.entity.Product
import com.flab.idolu.domain.product.exception.InsufficientStockException
import com.flab.idolu.domain.product.exception.ProductNotFoundException
import com.flab.idolu.domain.product.repository.ProductRepository
import spock.lang.Specification

import static com.flab.idolu.domain.fixture.ProductFixture.DEFAULT_PRODUCT
import static com.flab.idolu.domain.fixture.ProductFixture.DEFAULT_PRODUCT_DISTRIBUTED_LOCK
import static com.flab.idolu.domain.fixture.ProductFixture.DEFAULT_PRODUCT_FOR_UPDATE
import static com.flab.idolu.domain.fixture.ProductFixture.INSUFFICIENT_PRODUCT

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

    def "재고 차감 실패 테스트: 상품 없음"() {
        given:
        productRepository.findProductsByIdForUpdate(List.of(DEFAULT_PRODUCT)) >> List.of()

        when:
        productService.decreaseProductStocks(List.of(DEFAULT_PRODUCT))

        then:
        def exception = thrown(ProductNotFoundException)
        exception.message == "상품이 없습니다."
    }

    def "재고 차감 실패 테스트: 재고 부족"() {
        given:
        productRepository.findProductsByIdForUpdate(List.of(INSUFFICIENT_PRODUCT)) >> List.of(DEFAULT_PRODUCT)

        when:
        productService.decreaseProductStocks(List.of(INSUFFICIENT_PRODUCT))

        then:
        def exception = thrown(InsufficientStockException)
        exception.message == "재고는 0개 미만이 될 수 없습니다."
    }

    def "재고 차감 성공"() {
        given:
        productRepository.findProductsByIdForUpdate(List.of(DEFAULT_PRODUCT)) >> List.of(DEFAULT_PRODUCT)
        productRepository.findById(1L) >> Optional.of(DEFAULT_PRODUCT)

        when:
        productService.decreaseProductStocks(List.of(DEFAULT_PRODUCT))

        then:
        def product = productRepository.findById(1L)
        product.get().stock == 0
    }

    def "비관적 락을 사용한 재고 차감 실패 테스트: 상품 없음"() {
        given:
        productRepository.findByIdForUpdate(1L) >> Optional.empty()

        when:
        productService.decreaseStockWithPessimisticLock(1L, 1)

        then:
        def exception = thrown(ProductNotFoundException)
        exception.message == "상품이 없습니다."
    }

    def "비관적 락을 사용한 재고 차감 실패 테스트: 재고 부족"() {
        given:
        productRepository.findByIdForUpdate(1L) >> Optional.of(DEFAULT_PRODUCT)

        when:
        productService.decreaseStockWithPessimisticLock(1L, 10)

        then:
        def exception = thrown(InsufficientStockException)
        exception.message == "재고는 0개 미만이 될 수 없습니다."
    }

    def "비관적 락을 사용한 재고 차감 성공"() {
        given:
        productRepository.findByIdForUpdate(1L) >> Optional.of(DEFAULT_PRODUCT_FOR_UPDATE)
        productRepository.findById(1L) >> Optional.of(DEFAULT_PRODUCT_FOR_UPDATE)

        when:
        productService.decreaseStockWithPessimisticLock(1L, 3)

        then:
        def product = productRepository.findById(1L)
        product.get().stock == 0
    }

    def "분산 락을 사용한 재고 차감 실패 테스트: 상품 없음"() {
        given:
        productRepository.findById(1L) >> Optional.empty()

        when:
        productService.decreaseStockWithDistributedLock(1L, 1)

        then:
        def exception = thrown(ProductNotFoundException)
        exception.message == "상품이 없습니다."
    }

    def "분산 락을 사용한 재고 차감 성공"() {
        given:
        productRepository.findById(1L) >> Optional.of(DEFAULT_PRODUCT_DISTRIBUTED_LOCK)

        when:
        productService.decreaseStockWithDistributedLock(1L, 3)

        then:
        def product = productRepository.findById(1L)
        product.get().stock == 0
    }
}
