package com.example.ex25_branch.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ExceptionAspect {

	@AfterThrowing(pointcut = "execution(* com.example.ex25_branch.service.*.*(..))", throwing = "e")
	public void logError(JoinPoint jp, Exception e) {
		log.info("==== 메서드 실행 중 오류: {} ==== \n ==== {} ====", jp.getSignature(), e.getMessage());
	}
	
	
}
