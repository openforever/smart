package org.smart4j.framework.springaop;

import org.smart4j.framework.proxy.Greeting;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/18.
 * ͨ���ӿ�ʵ�ֵĺ���֪ͨ
 */
public class GreetingAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("After method");
    }
    public static void main(String[] args){
        ProxyFactory proxyFactory = new ProxyFactory();/*����������*/
        proxyFactory.setTarget(new GreetingImpl());/*����Ŀ�������*/
        /*���ǰ�á�����֪ͨ*/
        proxyFactory.addAdvice(new GreetingBeforeAdvice());
        proxyFactory.addAdvice(new GreetingAfterAdvice());

        /**
         * �Ӵ������л�ȡ���������ô���ķ���
         * ������������ж��Ŀ���������ô�� ?? @Todo
         */
        Greeting greeting = (Greeting) proxyFactory.getProxy();
        greeting.sayHello("Jack");
    }
}

class GreetingImpl implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello! " + name);
    }
}