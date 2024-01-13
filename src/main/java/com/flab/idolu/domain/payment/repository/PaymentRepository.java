package com.flab.idolu.domain.payment.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.flab.idolu.domain.payment.entity.Payment;

@Mapper
@Repository
public interface PaymentRepository {

	void insertPayment(Payment payment);
}
