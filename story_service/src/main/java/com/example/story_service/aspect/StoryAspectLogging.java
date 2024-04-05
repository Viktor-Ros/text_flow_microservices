package com.example.story_service.aspect;

import com.example.base_service.aspect.BaseAspectLogging;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StoryAspectLogging extends BaseAspectLogging {

    static final Logger logger = LoggerFactory.getLogger(StoryAspectLogging.class);


    @Around("execution(* com.example.story_service.*.*.*(..))")
    public Object aroundAllMethodAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String message = getMessage(proceedingJoinPoint);
        logger.info("START" + message);
        Object result = proceedingJoinPoint.proceed();
        logger.info("FINISH" + message + " RETURN RESULT = " +  result);

        return result;
    }

}
