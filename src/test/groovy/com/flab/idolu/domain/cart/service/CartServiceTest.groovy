package com.flab.idolu.domain.cart.service


import com.flab.idolu.domain.cart.exception.CartNotFoundException
import com.flab.idolu.domain.cart.repository.CartRepository
import com.flab.idolu.domain.member.exception.UnauthorizedMemberException
import com.flab.idolu.domain.product.exception.ProductNotFoundException
import com.flab.idolu.domain.product.repository.ProductRepository
import spock.lang.Specification

import static com.flab.idolu.domain.fixture.CartFixture.*
import static com.flab.idolu.domain.fixture.ProductFixture.DEFAULT_PRODUCT

class CartServiceTest extends Specification {

    CartService cartService
    CartRepository cartRepository = Mock()
    ProductRepository productRepository = Mock()

    def setup() {
        cartService = new CartService(cartRepository, productRepository)
    }

    def "카트 상품 등록 실패 테스트: #exceptionMessage"() {
        when:
        cartService.insertCart(cart, 1L)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.message == exceptionMessage

        where:
        cart                       | exceptionMessage
        INVALID_PRODUCT_ID_CART    | "상품 id를 입력해주세요."
        INVALID_QUANTITY_CART      | "수량을 입력해주세요."
        INSUFFICIENT_QUANTITY_CART | "수량이 0보다 커야 합니다."
    }

    def "카트 상품 등록 실패 테스트: 상품 없음"() {
        given:
        productRepository.findById(1L) >> Optional.empty()

        when:
        cartService.insertCart(DEFAULT_CART_PRODUCT_REQUEST, 1L)

        then:
        def exception = thrown(ProductNotFoundException)
        exception.message == "상품이 없습니다."
    }

    def "카트 상품 등록 성공 테스트: 카트 상품 존재"() {
        given:
        productRepository.findById(1L) >> Optional.of(DEFAULT_PRODUCT)
        cartRepository.findByProductIdAndMemberId(1L, 1L) >> Optional.of(DEFAULT_CART)

        when:
        cartService.insertCart(DEFAULT_CART_PRODUCT_REQUEST, 1L)

        then:
        1 * cartRepository.updateCartQuantity(_)
    }

    def "카트 상품 등록 성공 테스트: 카트에 상품 존재하지 않음"() {
        given:
        productRepository.findById(1L) >> Optional.of(DEFAULT_PRODUCT)
        cartRepository.findByProductIdAndMemberId(1L, 1L) >> Optional.empty()

        when:
        cartService.insertCart(DEFAULT_CART_PRODUCT_REQUEST, 1L)

        then:
        1 * cartRepository.insertCart(_)
    }

    def "카트 상품 조회 테스트"() {
        given:
        cartRepository.findByMemberId(1L) >> List.of(DEFAULT_CART_PRODUCT_RESPONSE)

        when:
        def cartProduct = cartService.findByMemberId(1L)

        then:
        cartProduct.size() == 1
        cartProduct.get(0).id == 1L
    }

    def "카트 수량 수정 실패 테스트: #exceptionMessage"() {
        when:
        cartService.updateCartQuantity(1L, cartQuantityRequest, 1L)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.message == exceptionMessage

        where:
        cartQuantityRequest                | exceptionMessage
        INVALID_QUANTITY_CART_REQUEST      | "수량을 입력해주세요."
        INSUFFICIENT_QUANTITY_CART_REQUEST | "수량이 0보다 커야 합니다."
    }

    def "카트 수량 수정 실패 테스트: 카트 상품 없음"() {
        given:
        cartRepository.findById(1L) >> Optional.empty()

        when:
        cartService.updateCartQuantity(1L, DEFAULT_CART_REQUEST, 1L)

        then:
        def exception = thrown(CartNotFoundException)
        exception.message == "존재하는 카트 상품이 없습니다."
    }

    def "카트 수량 수정 실패 테스트: 본인 상품 아님"() {
        given:
        cartRepository.findById(1L) >> Optional.of(DEFAULT_CART)

        when:
        cartService.updateCartQuantity(1L, DEFAULT_CART_REQUEST, 2L)

        then:
        def exception = thrown(UnauthorizedMemberException)
        exception.message == "본인의 카트 상품만 수량 수정이 가능합니다."
    }

    def "카트 수량 수정 성공 테스트"() {
        given:
        cartRepository.findById(1L) >> Optional.of(DEFAULT_CART)

        when:
        cartService.updateCartQuantity(1L, DEFAULT_CART_REQUEST, 1L)

        then:
        def findCart = cartRepository.findById(1L).get()
        findCart.quantity == 3
    }

    def "카트 삭제 실패 테스트: 상품 없음"() {
        given:
        cartRepository.findById(1L) >> Optional.empty()

        when:
        cartService.updateDeletedById(1L, 1L)

        then:
        def exception = thrown(CartNotFoundException)
        exception.message == "존재하는 카트 상품이 없습니다."
    }

    def "카트 삭제 실패 테스트: 본인 상품 아님"() {
        given:
        cartRepository.findById(1L) >> Optional.of(DEFAULT_CART)

        when:
        cartService.updateDeletedById(1L, 2L)

        then:
        def exception = thrown(UnauthorizedMemberException)
        exception.message == "본인의 카트 상품만 수량 수정이 가능합니다."
    }

    def "카트 삭제 성공 테스트"() {
        given:
        cartRepository.findById(1L) >> Optional.of(DEFAULT_CART)

        when:
        cartService.updateDeletedById(1L, 1L)

        then:
        1 * cartRepository.updateDeletedById(1L)
    }
}
