package com.kim.iobwa.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
	@Pointcut("execution(* com.kim.board.biz..*Impl.*(..))")
	public void aPointcut() {} // 포인트컷 선언
}