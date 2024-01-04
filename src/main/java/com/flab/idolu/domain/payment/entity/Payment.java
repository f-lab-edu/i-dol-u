package com.flab.idolu.domain.payment.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {

	private Long id;
	private Long orderId;
	private PaymentStatus paymentStatus;
	private PaymentType paymentType;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
