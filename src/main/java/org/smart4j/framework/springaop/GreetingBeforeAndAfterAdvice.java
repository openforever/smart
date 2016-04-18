package org.smart4j.framework.springaop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/18.
 * ֻ����һ����ǿ�࣬ͬʱʵ��ǰ��֪ͨ�ͺ���֪ͨ�����ӿ�,Ҳ���ǻ���֪ͨ�Ĺ���,��ר�ŵĽӿ�ʵ��
 */
public class GreetingBeforeAndAfterAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("After method");
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before method");
    }
}
