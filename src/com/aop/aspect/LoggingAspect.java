package com.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.aop.dao.*.*(..))")
    public void beforeAddAccountAdvice() {

        System.out.println("\n====>>> Executing @Before advice on addAccount()");
    }
}
