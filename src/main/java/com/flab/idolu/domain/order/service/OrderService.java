package com.flab.idolu.domain.order.service;

import static com.flab.idolu.global.util.ValidateDtoUtil.*;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.flab.idolu.domain.order.dto.request.OrderRequest;
import com.flab.idolu.domain.order.entity.Order;
import com.flab.idolu.domain.order.entity.OrderProduct;
import com.flab.idolu.domain.order.repository.OrderRepository;
import com.flab.idolu.domain.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final ProductService productService;

	@Transactional
	public void placeOrder(OrderRequest orderRequest, Long memberId) {
		validateOrderRequest(orderRequest);

		Order order = orderRequest.toEntity(memberId);
		orderRepository.insertOrder(order);
		List<OrderProduct> orderProducts = orderRequest.toOrderProductEntity(order.getId());
		orderRepository.insertOrderProduct(orderProducts);

		orderProducts.forEach(orderProduct ->
			productService.updateProductStock(orderProduct.getProductId(), orderProduct.getQuantity()));
	}

	private void validateOrderRequest(OrderRequest orderRequest) {
		validateRecipient(orderRequest.getRecipient());
		validateZipCode(orderRequest.getZipCode());
		validateAddress1(orderRequest.getAddress1());
		validatePhone(orderRequest.getPhone());
		Assert.isTrue(!orderRequest.getOrderLineItems().isEmpty(), "구매 상품이 없습니다.");
	}
}
