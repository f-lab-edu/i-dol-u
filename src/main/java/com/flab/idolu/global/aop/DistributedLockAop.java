package com.flab.idolu.global.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import com.flab.idolu.global.annotation.DistributedLock;
import com.flab.idolu.global.common.AopForTransaction;
import com.flab.idolu.global.exception.RedissonLockTimeoutException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributedLockAop {

	private final RedissonClient redissonClient;
	private final AopForTransaction aopForTransaction;

	@Around("@annotation(com.flab.idolu.global.annotation.DistributedLock)")
	public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

		String baseKey = distributedLock.lockName();
		String dynamicKey = generateDynamicKey(signature.getParameterNames(), joinPoint.getArgs(),
			distributedLock.identifier());
		RLock rLock = redissonClient.getLock(baseKey + ":" + dynamicKey);

		try {
			boolean available = rLock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(),
				distributedLock.timeUnit());
			if (!available) {
				throw new RedissonLockTimeoutException("Redisson Lock을 획득하지 못했습니다.");
			}

			return aopForTransaction.proceed(joinPoint);
		} finally {
			try {
				rLock.unlock();
			} catch (IllegalMonitorStateException e) { // leaseTime이 지난 경우 자동 해제가 되는데 unlock 시도 시 발생
				log.info(e + baseKey + dynamicKey);
			}
		}
	}

	private String generateDynamicKey(String[] parameterNames, Object[] args, String identifier) {
		for (int i = 0; i < parameterNames.length; i++) {
			if (parameterNames[i].equals(identifier)) {
				return String.valueOf(args[i]);
			}
		}

		throw new IllegalArgumentException("해당하는 identifier가 없습니다.");
	}
}
