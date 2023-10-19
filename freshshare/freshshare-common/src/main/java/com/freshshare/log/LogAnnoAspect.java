package com.freshshare.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAnnoAspect {

    @Pointcut("@annotation(com.freshshare.log.LogAnno)")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        log.info("before");
        log.info("Request Method:{}, Request Param: {}", joinPoint.getSignature(), JSON.toJSON(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "o",pointcut = "pointcut()")
    public void after(Object o){
        log.info("afterReturning");
        log.info("Response: {}", JSON.toJSON(o));
    }

    @AfterThrowing(pointcut = "pointcut()",throwing = "e")
    public void afterThrowing(Exception e){
        log.error("afterThrowing");
        log.error("Exception message:{} ", e.getMessage());
    }

}
