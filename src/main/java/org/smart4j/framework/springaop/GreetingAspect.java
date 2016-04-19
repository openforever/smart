package org.smart4j.framework.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by snow on 2016/4/19.
 * Spring集成AspectJ，不需要定义AspectJ类(扩展了java语法的一种新语言，还需要特定的编译器)
 * 只需要使用特定的AspectJ切点表达式即可
 * @author openforever
 */
@Aspect /*声明Aspect切面类, 就是Advisor，该类无需实现任何接口，只需要定义一个方法即可(什么名字无所谓)*/
@Component
public class GreetingAspect {

    /**
     * 可读性比正则表达式强
     * GreetingImpl.*  拦截GreetingImpl下的所有的方法,匹配特定方法，将*改为方法名
     * <aop:aspectj-autoproxy proxy-target-class="true"/>
     * 这一行配置，在加上component-scan扫描包两行配置就可以，不需要配置大量的代理
     *  默认值为false：只能代理接口(JDK动态代理)
     *  true:代理目标类(CGLib动态代理)
     * (..)表示方法的参数任意
     * @param pjp
     * @return 拦截的方法的返回值
     * @throws Throwable
     */
    @Around("execution(* org.smart4j.framework.springaop.GreetingImpl.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        before();
        Object result = pjp.proceed();/*ProceedingJoinPoint 连接点, 可以通过该对象获取方法的任何信息*/
        after();
        return result;
    }

    /**
     * 拦截指定的注解的方法
     * annotation(定义需要拦截的注解名)
     * 如果有匹配的类包含多个匹配的方法，可以给方法添加注解，然后拦截指定注解的方法
     * 相关注解：
     * Before, After, Around, AfterThrowing(抛出增强), DeclareParents(引入增强）
     * AfterReturning(返回后增强),也可以理解为Finally增强,比After更晚些
     */
    @Before("@annotation(org.smart4j.framework.springaop.Tag)")
    public Object before(ProceedingJoinPoint pjp) throws Throwable {
        before();
        return pjp.proceed();
    }

    private void after() {
        System.out.println("After AspectJ");
    }

    private void before() {
        System.out.println("Before AspectJ");
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Tag{

}
