package org.smart4j.framework.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

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

    private void after() {
        System.out.println("After AspectJ");
    }

    private void before() {
        System.out.println("Before AspectJ");
    }
}
