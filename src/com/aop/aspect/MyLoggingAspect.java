package com.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyLoggingAspect {

    @Before("com.aop.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    //@Before("execution(* com.aop.dao.*.*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("\n====>>> Executing @Before advice on addAccount()");
    }
}
