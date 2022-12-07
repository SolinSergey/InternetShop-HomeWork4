package ru.gb.internetshop.proxy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AppLoggingAspect {

    @Getter
    private long productServiceProfilingTime;
    @Getter
    private long userServiceProfilingTime;
    @Around("execution(public * ru.gb.internetshop.services.ProductService.*(..))")
    public Object methodProfilingProductService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long begin = System.currentTimeMillis();
        Object out=proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        productServiceProfilingTime +=duration;
        return out;
    }

    @Around("execution(public * ru.gb.internetshop.services.UserService.*(..))")
    public Object methodProfilingUserService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long begin = System.currentTimeMillis();
        Object out=proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        userServiceProfilingTime +=duration;
        return out;
    }

    public void profilingInit(){
        productServiceProfilingTime = 0;
    }
}
