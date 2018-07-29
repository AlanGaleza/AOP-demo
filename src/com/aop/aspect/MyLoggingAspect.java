package com.aop.aspect;

import com.aop.model.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyLoggingAspect {

    @After("execution(* com.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.aop.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc){
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>> Executing @AfterThrowing on method: " + method);
        System.out.println("\n====>>> The exception is: " + theExc);

    }

    @AfterReturning(
            pointcut = "execution(* com.aop.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>> Executing @AfterReturning on method: " + method);
        System.out.println("\n====>>> result is: " + result);

        convertAccountNamesToUpperCase(result);

        System.out.println("\n====>>> result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        for (Account tempAccount : result) {
            String theUpperName = tempAccount.getName().toUpperCase();
            tempAccount.setName(theUpperName);
        }
    }

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
