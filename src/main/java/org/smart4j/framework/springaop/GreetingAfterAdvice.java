package org.smart4j.framework.springaop;

import org.smart4j.framework.proxy.Greeting;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/18.
 * 通过接口实现的后置通知
 */
@Component
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
         * 应该是每个ProxyFactory对应一个目标类对象
         */
        Greeting greeting = (Greeting) proxyFactory.getProxy();
        greeting.sayHello("Jack");

        //使用配置文件的方式
        /**
         *  获取Spring context
         *  将applicationContext.xml放入resources资源目录下就能运行
         *  放入springaop当前包下，怎么指定路径? @Todo
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //greetingProxy 就是ProxyFactoryBean的实例，也就是通过实现接口方式中的ProxyFactory
        Greeting greetingProxy = (Greeting) context.getBean("greetingProxy");
        greeting.sayHello("Jack");

    }
}

/**
 * 父接口Greeting不需要添加注解Component，实现类添加就能放入bean容器中
 * 不然实现了很多接口，岂不得每个接口都添加注解?
 * @author openforever
 */
@Component
class GreetingImpl implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello! " + name);
    }
}