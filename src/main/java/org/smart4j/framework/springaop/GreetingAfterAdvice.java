package org.smart4j.framework.springaop;

import org.smart4j.framework.proxy.Greeting;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/18.
 * 通过接口实现的后置通知
 */
public class GreetingAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("After method");
    }
    public static void main(String[] args){
        ProxyFactory proxyFactory = new ProxyFactory();/*创建代理工厂*/
        proxyFactory.setTarget(new GreetingImpl());/*射入目标类对象*/
        /*添加前置、后置通知*/
        proxyFactory.addAdvice(new GreetingBeforeAdvice());
        proxyFactory.addAdvice(new GreetingAfterAdvice());

        /**
         * 从代理工厂中获取代理，并调用代理的方法
         * 如果代理工厂中有多个目标类对象怎么办 ?? @Todo
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