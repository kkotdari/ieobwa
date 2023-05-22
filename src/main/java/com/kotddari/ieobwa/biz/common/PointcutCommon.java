package com.kotddari.ieobwa.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
	@Pointcut("execution(* com.kim.iobwa.biz..*Impl.*(..))")
	public void aPointcut() {} // 포인트컷 선언
}