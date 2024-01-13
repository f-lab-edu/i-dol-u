package com.flab.idolu.domain.order.dto.request;

import java.math.BigDecimal;
import java.util.List;

import com.flab.idolu.domain.order.entity.Order;
import com.flab.idolu.domain.order.entity.OrderProduct;
import com.flab.idolu.domain.order.entity.OrderStatus;
import com.flab.idolu.domain.payment.entity.Payment;
import com.flab.idolu.domain.payment.entity.PaymentStatus;
import com.flab.idolu.domain.payment.entity.PaymentType;
import com.flab.idolu.domain.product.entity.Product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderRequest {

	private String recipient;
	private String phone;
	private String zipCode;
	private String address1;
	private String address2;
	private List<OrderLineItemDto> orderLineItems;
	private String paymentType;

	public Order toEntity(Long memberId) {
		return Order.builder()
			.memberId(memberId)
			.recipient(recipient)
			.phone(phone)
			.zipCode(zipCode)
			.address1(address1)
			.address2(address2)
			.orderStatus(OrderStatus.ORDERED)
			.totalPrice(orderLineItems.stream()
				.map(orderLineItemDto -> orderLineItemDto.getPrice()
					.multiply(BigDecimal.valueOf(orderLineItemDto.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add))
			.totalQuantity(orderLineItems.stream().map(OrderLineItemDto::getQuantity).reduce(0, Integer::sum))
			.build();
	}

	public List<Product> toProductEntity() {
		return orderLineItems.stream()
			.map(orderLineItemDto -> Product.builder()
				.id(orderLineItemDto.getProductId())
				.stock(orderLineItemDto.getQuantity())
				.build())
			.toList();
	}

	public List<OrderProduct> toOrderProductEntity(Long orderId) {
		return orderLineItems.stream()
			.map(orderLineItemDto -> OrderProduct.builder()
				.orderId(orderId)
				.productId(orderLineItemDto.getProductId())
				.quantity(orderLineItemDto.getQuantity())
				.price(orderLineItemDto.getPrice())
				.build())
			.toList();
	}

	public Payment toPaymentEntity(Long orderId) {
		return Payment.builder()
			.orderId(orderId)
			.paymentType(PaymentType.valueOf(paymentType))
			.paymentStatus(PaymentStatus.COMPLETE)
			.build();
	}
}
