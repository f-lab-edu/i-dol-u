package com.flab.idolu.domain.order.service


import com.flab.idolu.domain.order.repository.OrderRepository
import com.flab.idolu.domain.product.exception.InsufficientStockException
import com.flab.idolu.domain.product.exception.ProductNotFoundException
import com.flab.idolu.domain.product.repository.ProductRepository
import com.flab.idolu.domain.product.service.ProductService
import spock.lang.Specification

import static com.flab.idolu.domain.fixture.OrderFixture.*
import static com.flab.idolu.domain.fixture.ProductFixture.DEFAULT_PRODUCT_FOR_ORDER
import static java.util.Optional.of

class OrderServiceTest extends Specification {

    OrderService orderService;
    ProductService productService;
    OrderRepository orderRepository = Mock()
    ProductRepository productRepository = Mock()

    def setup() {
        productService = new ProductService(productRepository)
        orderService = new OrderService(orderRepository, productService)
    }

    def "주문 유효성 검증 실패 테스트: #exceptionMessage"() {
        when:
        orderService.placeOrder(orderRequest, 1L)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.message == exceptionMessage

        where:
        orderRequest                  | exceptionMessage
        BLANK_RECIPIENT_ORDER_REQUEST | "수령자를 입력해야 합니다."
        BLANK_PHONE_ORDER_REQUEST     | "휴대전화를 입력해야 합니다."
        BLANK_ZIPCODE_ORDER_REQUEST   | "우편번호를 입력해야 합니다."
        BLANK_ADDRESS1_ORDER_REQUEST  | "기본주소를 입력해야 합니다."
        INVALID_PHONE_ORDER_REQUEST   | "휴대전화 양식에 맞춰야 합니다."
        EMPTY_ITEMS_ORDER_REQUEST     | "구매 상품이 없습니다."
    }

    def "주문 실패 테스트: 재고 없음"() {
        given:
        productRepository.findById(1L) >> Optional.empty()

        when:
        orderService.placeOrder(DEFAULT_ORDER_REQUEST, 1L)

        then:
        def exception = thrown(ProductNotFoundException)
        exception.message == "상품이 없습니다."
    }

    def "주문 실패 테스트: 재고 부족"() {
        given:
        productRepository.findById(1L) >> of(DEFAULT_PRODUCT_FOR_ORDER)

        when:
        orderService.placeOrder(INSUFFICIENT_ORDER_REQUEST, 1L)

        then:
        def exception = thrown(InsufficientStockException)
        exception.message == "재고는 0개 미만이 될 수 없습니다."
    }

    def "주문 성공 테스트"() {
        given:
        productRepository.findById(1L) >> of(DEFAULT_PRODUCT_FOR_ORDER)

        when:
        orderService.placeOrder(DEFAULT_ORDER_REQUEST, 1L)

        then:
        def product = productRepository.findById(1L)
        product.get().stock == 2
    }
}