package com.hzq.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 切片
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.hzq.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("TimeAspect start");
        long begin = System.currentTimeMillis();
        Object[] args = pjp.getArgs();
        for (Object arg:args) {
            System.out.println("arg is "+arg);
        }
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("time filter 耗时"+String.valueOf(end-begin));
        System.out.println("TimeAspect end");
        return proceed;
    }
}
