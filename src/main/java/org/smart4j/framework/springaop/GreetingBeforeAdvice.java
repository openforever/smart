package org.smart4j.framework.springaop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/18.
 * ����Sping AOPʵ��ǰ��֪ͨ(��ǿ):��ԭ�д��빦�ܵ�һ�֡���ǿ��
 */
@Component
public class GreetingBeforeAdvice implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before method");
    }
}
