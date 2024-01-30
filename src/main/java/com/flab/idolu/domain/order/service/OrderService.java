package com.flab.idolu.domain.order.service;

import static com.flab.idolu.global.util.ValidateDtoUtil.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.flab.idolu.domain.order.dto.request.OrderRequest;
import com.flab.idolu.domain.order.dto.response.OrderInfoResponse;
import com.flab.idolu.domain.order.dto.response.OrderListResponse;
import com.flab.idolu.domain.order.entity.Order;
import com.flab.idolu.domain.order.entity.OrderProduct;
import com.flab.idolu.domain.order.exception.InvalidOrderOwnerException;
import com.flab.idolu.domain.order.repository.OrderRepository;
import com.flab.idolu.domain.payment.entity.PaymentType;
import com.flab.idolu.domain.payment.service.PaymentService;
import com.flab.idolu.domain.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final ProductService productService;
	private final PaymentService paymentService;

	@Transactional
	public void placeOrder(OrderRequest orderRequest, Long memberId) {
		validateOrderRequest(orderRequest);

		Order order = orderRequest.toEntity(memberId);
		orderRepository.insertOrder(order);
		List<OrderProduct> orderProducts = orderRequest.toOrderProductEntity(order.getId());
		orderRepository.insertOrderProduct(orderProducts);

		productService.decreaseProductStocks(orderRequest.toProductEntity());
		paymentService.insertPayment(orderRequest.toPaymentEntity(order.getId()));
	}

	@Transactional(readOnly = true)
	public List<OrderListResponse> findByMemberId(Long memberId, int size, int offset) {

		return orderRepository.findByMemberId(memberId, size, size * offset);
	}

	@Transactional(readOnly = true)
	public OrderInfoResponse findById(Long id, Long memberId) {
		OrderInfoResponse orderInfoResponse = orderRepository.findById(id);

		if (!orderInfoResponse.getMemberId().equals(memberId)) {
			throw new InvalidOrderOwnerException("본인이 구매하지 않은 상품은 조회할 수 없습니다.");
		}

		return orderInfoResponse;
	}

	private void validateOrderRequest(OrderRequest orderRequest) {
		validateRecipient(orderRequest.getRecipient());
		validateZipCode(orderRequest.getZipCode());
		validateAddress1(orderRequest.getAddress1());
		validatePhone(orderRequest.getPhone());
		Assert.isTrue(!orderRequest.getOrderLineItems().isEmpty(), "구매 상품이 없습니다.");
		validatePaymentType(orderRequest.getPaymentType());
	}

	private void validatePaymentType(String paymentType) {
		Arrays.stream(PaymentType.values())
			.filter(type -> type.name().equals(paymentType))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("%s에 해당하는 결제 타입이 없습니다.".formatted(paymentType)));
	}
}
