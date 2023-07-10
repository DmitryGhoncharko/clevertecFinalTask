package ru.clevertec.ecl.clevertecfinaltask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* ru.clevertec.ecl.clevertecfinaltask.controller.*.*(..))")
    public void logBeforeControllers(JoinPoint joinPoint) {
        logger.info("Calling method: {}", joinPoint.getSignature().toShortString());
        logger.info("Arguments: {}", joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* ru.clevertec.ecl.clevertecfinaltask.controller.*.*(..))",
            returning = "result")
    public void logAfterReturningControllers(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned: {}", joinPoint.getSignature().toShortString(), result);
    }
    @Before("execution(* ru.clevertec.ecl.clevertecfinaltask.service.impl.*.*(..))")
    public void logBeforeService(JoinPoint joinPoint) {
        logger.info("Calling method: {}", joinPoint.getSignature().toShortString());
        logger.info("Arguments: {}", joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* ru.clevertec.ecl.clevertecfinaltask.service.impl.*.*(..))",
            returning = "result")
    public void logAfterReturningService(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned: {}", joinPoint.getSignature().toShortString(), result);
    }
}

