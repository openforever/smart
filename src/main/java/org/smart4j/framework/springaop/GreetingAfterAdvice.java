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
 * ͨ���ӿ�ʵ�ֵĺ���֪ͨ
 */
@Component
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
         * Ӧ����ÿ��ProxyFactory��Ӧһ��Ŀ�������
         */
        Greeting greeting = (Greeting) proxyFactory.getProxy();
        greeting.sayHello("Jack");

        //ʹ�������ļ��ķ�ʽ
        /**
         *  ��ȡSpring context
         *  ��applicationContext.xml����resources��ԴĿ¼�¾�������
         *  ����springaop��ǰ���£���ôָ��·��? @Todo
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //greetingProxy ����ProxyFactoryBean��ʵ����Ҳ����ͨ��ʵ�ֽӿڷ�ʽ�е�ProxyFactory
        Greeting greetingProxy = (Greeting) context.getBean("greetingProxy");
        greeting.sayHello("Jack");

    }
}

/**
 * ���ӿ�Greeting����Ҫ���ע��Component��ʵ������Ӿ��ܷ���bean������
 * ��Ȼʵ���˺ܶ�ӿڣ��񲻵�ÿ���ӿڶ����ע��?
 * @author openforever
 */
@Component
class GreetingImpl implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello! " + name);
    }
}