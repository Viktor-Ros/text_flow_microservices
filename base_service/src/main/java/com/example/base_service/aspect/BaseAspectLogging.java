package com.example.base_service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public abstract class BaseAspectLogging {

    static final Logger logger = LoggerFactory.getLogger(BaseAspectLogging.class);


    @Around("execution(* com.example.base_service.*.*.*(..))")
    public Object aroundBaseAllMethodAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String message = getMessage(proceedingJoinPoint);
        logger.info("START" + message);
        Object result = proceedingJoinPoint.proceed();
        logger.info("FINISH" + message + " RETURN RESULT = " +  result);

        return result;
    }

    protected String getMethodParams(ProceedingJoinPoint proceedingJoinPoint){
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        Object[] args = proceedingJoinPoint.getArgs();
        String[] parametersName = methodSignature.getParameterNames();
        StringBuilder sbParams = new StringBuilder();

        for(int i = 0; i < args.length; i++){
            sbParams.append(parametersName[i]).append(" = ").append(args[i]);
            if(i != args.length - 1){
                sbParams.append(", ");
            }
        }

        return sbParams.toString();
    }

    protected String getMessage(ProceedingJoinPoint proceedingJoinPoint){
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        return " of " + methodSignature.getDeclaringTypeName() + "." + methodSignature.getName() + "("+ getMethodParams(proceedingJoinPoint) + ");";
    }

}
