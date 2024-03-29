package com.flab.idolu.domain.order.service

import com.flab.idolu.domain.order.dto.response.OrderInfoResponse
import com.flab.idolu.domain.order.exception.InvalidOrderOwnerException
import com.flab.idolu.domain.order.repository.OrderRepository
import com.flab.idolu.domain.payment.repository.PaymentRepository
import com.flab.idolu.domain.payment.service.PaymentService
import com.flab.idolu.domain.product.repository.ProductRepository
import com.flab.idolu.domain.product.service.ProductService
import spock.lang.Specification

import static com.flab.idolu.domain.fixture.OrderFixture.*
import static com.flab.idolu.domain.fixture.ProductFixture.DEFAULT_PRODUCT_FOR_ORDER

class OrderServiceTest extends Specification {

    OrderService orderService;
    ProductService productService;
    PaymentService paymentService;
    OrderRepository orderRepository = Mock()
    ProductRepository productRepository = Mock()
    PaymentRepository paymentRepository = Mock()

    def setup() {
        productService = new ProductService(productRepository)
        paymentService = new PaymentService(paymentRepository)
        orderService = new OrderService(orderRepository, productService, paymentService)
    }

    def "주문 유효성 검증 실패 테스트: #exceptionMessage"() {
        when:
        orderService.placeOrder(orderRequest, 1L)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.message == exceptionMessage

        where:
        orderRequest                       | exceptionMessage
        BLANK_RECIPIENT_ORDER_REQUEST      | "수령자를 입력해야 합니다."
        BLANK_PHONE_ORDER_REQUEST          | "휴대전화를 입력해야 합니다."
        BLANK_ZIPCODE_ORDER_REQUEST        | "우편번호를 입력해야 합니다."
        BLANK_ADDRESS1_ORDER_REQUEST       | "기본주소를 입력해야 합니다."
        INVALID_PHONE_ORDER_REQUEST        | "휴대전화 양식에 맞춰야 합니다."
        EMPTY_ITEMS_ORDER_REQUEST          | "구매 상품이 없습니다."
        INVALID_PAYMENT_TYPE_ORDER_REQUEST | "PAY에 해당하는 결제 타입이 없습니다."
    }

    def "주문 성공 테스트"() {
        given:
        productRepository.findProductsByIdForUpdate(List.of(DEFAULT_PRODUCT_FOR_ORDER)) >> List.of(DEFAULT_PRODUCT_FOR_ORDER)
        productRepository.findById(1L) >> Optional.of(DEFAULT_PRODUCT_FOR_ORDER)

        when:
        orderService.placeOrder(DEFAULT_ORDER_REQUEST, 1L)

        then:
        def product = productRepository.findById(1L)
        product.get().stock == 2
    }

    def "주문 목록 조회 테스트"() {
        when:
        orderService.findByMemberId(1L, 1, 1)

        then:
        1 * orderRepository.findByMemberId(1L, 1, 1)
    }

    def "주문 상세 조회 실패 테스트"() {
        given:
        orderRepository.findById(1L) >> INVALID_MEMBER_ID_RESPONSE

        when:
        orderService.findById(1L, 1L)

        then:
        def exception = thrown(InvalidOrderOwnerException)
        exception.getMessage() == "본인이 구매하지 않은 상품은 조회할 수 없습니다."
    }

    def "주문 상세 조회 성공 테스트"() {
        given:
        orderRepository.findById(1L) >> VALID_ORDER_INFO_RESPONSE

        when:
        def orderInfoResponse = orderService.findById(1L, 1L)

        then:
        orderInfoResponse.id == 1L
        orderInfoResponse.memberId == 1L
        orderInfoResponse.memberName == "oneny"
    }
}
