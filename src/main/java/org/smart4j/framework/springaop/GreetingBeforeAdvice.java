package org.smart4j.framework.springaop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/18.
 * 利用Sping AOP实现前置通知(增强):对原有代码功能的一种“增强”
 */
@Component
public class GreetingBeforeAdvice implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before method");
    }
}
