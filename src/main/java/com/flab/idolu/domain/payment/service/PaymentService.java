package com.flab.idolu.domain.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.idolu.domain.payment.entity.Payment;
import com.flab.idolu.domain.payment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;

	@Transactional
	public void insertPayment(Payment payment) {
		paymentRepository.insertPayment(payment);
	}
}
