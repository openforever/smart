package org.smart4j.framework.springaop;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/18.
 * 程序出错，抛出异常，一般是打印到控制台或者日志文件中，这样很多地方都得去处理，声明抛出异常通知，一劳永逸
 * ThrowsAdvice继承了AfterAdvice接口
 * spring aop:中的所有XxxAdvice接口，都只是标识性接口，里面什么都没有
 * @author openforever
 * @since 1.0.0
 */
@Component
public class GreetingThrowAdvice implements ThrowsAdvice{

    /**
     * 在接口方法中可以获取方法、参数、目标对象、异常对象等信息
     * 哪个对象的哪个方法(方法的参数列表)抛出了什么异常
     * @param method
     * @param args
     * @param target
     * @param e
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception e){
        System.out.println("-------------Throw Exception-------------");
        System.out.println("Target Class: " + target.getClass().getName());
        System.out.println("Method Name: " + method.getName());
        System.out.println("Exception Message: " + e.getMessage());
        System.out.println("-----------------------------------------");
    }
}
