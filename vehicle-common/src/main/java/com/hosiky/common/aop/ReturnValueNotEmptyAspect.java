package com.hosiky.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * 统一校验 service.impl 层返回值是否为空
 */
@Slf4j
@Aspect
@Component
public class ReturnValueNotEmptyAspect {

    /** 切点：service.impl 包下所有 public 方法 */
    @Pointcut("execution(public * com.hosiky.*.service.impl..*(..))")
    public void serviceReturnValue() {}

    @AfterReturning(pointcut = "serviceReturnValue()", returning = "ret")
    public void checkNotEmpty(JoinPoint jp, Object ret) {
        if (isEmpty(ret)) {
            String className = jp.getTarget().getClass().getSimpleName();
            String methodName = jp.getSignature().getName();

            String msg = String.format(
                    ">>> [Service-Return-Empty] %s.%s returned empty value",
                    className, methodName);

            log.error(msg);
            throw new IllegalStateException(msg);
        }
    }

    /** 判断各种常见“空”语义 */
    private boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        if (obj instanceof Optional) {
            return !((Optional<?>) obj).isPresent();
        }
        if (obj.getClass().isArray()) {
            return java.lang.reflect.Array.getLength(obj) == 0;
        }
        // 其它类型一律视为“非空”
        return false;
    }
}