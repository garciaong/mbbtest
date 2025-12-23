package com.mbb.test.aop;

import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Aspect
@Component
public class RequestResponseLogginAspect {

    private static final Logger log = LoggerFactory.getLogger(RequestResponseLogginAspect.class);

    @Around("@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object logPostPutRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request =
            ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpServletResponse httpResponse =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        Gson gson = new Gson();

        log.info("Incoming Request: Method={}, URI={}, Body={}",
            request.getMethod(), request.getRequestURI(), getRequestBody(joinPoint, gson));

        
        Object result = null;
        try {
            // Proceed with the actual method execution
            result = joinPoint.proceed(); 
        } catch (Throwable throwable) {
            log.error("Exception in method {}: {}", joinPoint.getSignature().getName(), throwable.getMessage());
            throw throwable;
        } finally {
            if (httpResponse != null) {
                log.info("Outgoing Response: Status={}, Body={}", httpResponse.getStatus(), gson.toJson(result));
            }
        }

        return result;
    }


    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object logGetRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpServletResponse httpResponse =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        Gson gson = new Gson();

        log.info("Incoming Request: Method={}, URI={}, Parameters={}",
                request.getMethod(), request.getRequestURI(), getRequestParameters(request));

        Object result = null;
        try {
            // Proceed with the actual method execution
            result = joinPoint.proceed(); 
        } catch (Throwable throwable) {
            log.error("Exception in method {}: {}", joinPoint.getSignature().getName(), throwable.getMessage());
            throw throwable;
        } finally {
            if (httpResponse != null) {
                log.info("Outgoing Response: Status={}, Body={}", httpResponse.getStatus(), gson.toJson(result));
            }
        }

        return result;
    }

    private String getRequestBody(ProceedingJoinPoint joinPoint, Gson gson) {
        StringBuilder requestBody = new StringBuilder();

        for (Object arg : joinPoint.getArgs()) {
            if (arg != null) {
                requestBody.append(gson.toJson(arg));
            }
        }
        return requestBody.toString().trim();
    }

    private String getRequestParameters(HttpServletRequest request) {
        StringBuilder requestBody = new StringBuilder();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> {
            if (request.getParameter(paramName) != null) {
                requestBody.append("[").append(paramName).append("=").append(request.getParameter(paramName)).append("]");
            }
        });
        return requestBody.toString().trim().length() == 0 ? "No Parameters" : requestBody.toString().trim();
    }
}
