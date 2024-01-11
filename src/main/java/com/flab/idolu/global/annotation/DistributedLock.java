package com.flab.idolu.global.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

	String lockName(); // 락의 이름

	String identifier(); // 분산락을 걸 파라미터 네임

	TimeUnit timeUnit() default TimeUnit.SECONDS; // 락의 시간 단위

	long waitTime() default 15L; // 락 대기 시간

	long leaseTime() default 14L; // 락 임대 시간
}
