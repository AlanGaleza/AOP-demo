package com.aop.aspect;

import com.aop.model.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyLoggingAspect {

    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Around("execution(* com.aop.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
        String method = theProceedingJoinPoint.getSignature().toShortString();
        myLogger.info("\n====>>> Executing @Around on method: " + method);

        long begin = System.currentTimeMillis();

        Object result = null;

        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Exception e) {
            myLogger.warning(e.getMessage());

            //result = "Major accident! but now worries its fine";

            //rethrow exception to mainapp
            throw e;
        }

        long end = System.currentTimeMillis();

        long duration = end - begin;
        myLogger.info("\n====>>> Duration: " + duration / 1000.0 + " seconds");
        return result;

    }

    @After("execution(* com.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint) {
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("\n====>>> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.aop.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc){
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("\n====>>> Executing @AfterThrowing on method: " + method);
        myLogger.info("\n====>>> The exception is: " + theExc);

    }

    @AfterReturning(
            pointcut = "execution(* com.aop.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result) {
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("\n====>>> Executing @AfterReturning on method: " + method);
        myLogger.info("\n====>>> result is: " + result);

        convertAccountNamesToUpperCase(result);

        myLogger.info("\n====>>> result is: " + result);
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
        myLogger.info("\n====>>> Executing @Before advice on addAccount()");

        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

        //display the metod signature
        myLogger.info("Method: " + methodSig);

        //display method arguments
        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {
            myLogger.info(tempArg.toString());

            if(tempArg instanceof Account) {

                //downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;

                myLogger.info("account name: " + theAccount.getName());
                myLogger.info("account name: " + theAccount.getLevel());
            }
        }
    }
}
