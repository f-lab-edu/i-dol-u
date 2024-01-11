package com.flab.idolu.global.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AopForTransaction {

	/**
	 * 트랜잭션을 호출한 상위 콜스택에서 발생하기 때문에
	 * leaseTime 보다 트랜잭션 timeout을 작게 설정해야 leaseTimeout 전에 rollback 실행
	 */
	@Transactional(timeout = 13)
	public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {
		return joinPoint.proceed();
	}
}
