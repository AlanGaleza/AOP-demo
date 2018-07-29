package com.aop.aspect;

import com.aop.model.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyLoggingAspect {

    @Before("com.aop.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    //@Before("execution(* com.aop.dao.*.*(..))")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n====>>> Executing @Before advice on addAccount()");

        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

        //display the metod signature
        System.out.println("Method: " + methodSig);

        //display method arguments
        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {
            System.out.println(tempArg);

            if(tempArg instanceof Account) {

                //downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account name: " + theAccount.getLevel());
            }
        }
    }
}
