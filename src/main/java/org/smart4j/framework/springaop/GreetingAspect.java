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
 * Spring����AspectJ������Ҫ����AspectJ��(��չ��java�﷨��һ�������ԣ�����Ҫ�ض��ı�����)
 * ֻ��Ҫʹ���ض���AspectJ�е���ʽ����
 * @author openforever
 */
@Aspect /*����Aspect������, ����Advisor����������ʵ���κνӿڣ�ֻ��Ҫ����һ����������(ʲô��������ν)*/
@Component
public class GreetingAspect {

    /**
     * �ɶ��Ա�������ʽǿ
     * GreetingImpl.*  ����GreetingImpl�µ����еķ���,ƥ���ض���������*��Ϊ������
     * <aop:aspectj-autoproxy proxy-target-class="true"/>
     * ��һ�����ã��ڼ���component-scanɨ����������þͿ��ԣ�����Ҫ���ô����Ĵ���
     *  Ĭ��ֵΪfalse��ֻ�ܴ���ӿ�(JDK��̬����)
     *  true:����Ŀ����(CGLib��̬����)
     * (..)��ʾ�����Ĳ�������
     * @param pjp
     * @return ���صķ����ķ���ֵ
     * @throws Throwable
     */
    @Around("execution(* org.smart4j.framework.springaop.GreetingImpl.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        before();
        Object result = pjp.proceed();/*ProceedingJoinPoint ���ӵ�, ����ͨ���ö����ȡ�������κ���Ϣ*/
        after();
        return result;
    }

    /**
     * ����ָ����ע��ķ���
     * annotation(������Ҫ���ص�ע����)
     * �����ƥ�����������ƥ��ķ��������Ը��������ע�⣬Ȼ������ָ��ע��ķ���
     * ���ע�⣺
     * Before, After, Around, AfterThrowing(�׳���ǿ), DeclareParents(������ǿ��
     * AfterReturning(���غ���ǿ),Ҳ�������ΪFinally��ǿ,��After����Щ
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
