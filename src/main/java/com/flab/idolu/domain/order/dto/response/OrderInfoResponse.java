package com.flab.idolu.domain.order.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.flab.idolu.domain.order.entity.OrderStatus;
import com.flab.idolu.domain.payment.entity.PaymentStatus;
import com.flab.idolu.domain.payment.entity.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoResponse {

	private Long id;
	private Long memberId;
	private String memberName;
	private String recipient;
	private String zipCode;
	private String address1;
	private String address2;
	private String phone;
	private OrderStatus orderStatus;
	private BigDecimal totalPrice;
	private int totalQuantity;
	private LocalDateTime createdAt;
	private PaymentType paymentType;
	private PaymentStatus paymentStatus;
	private List<OrderProductDto> orderProductList;
}
