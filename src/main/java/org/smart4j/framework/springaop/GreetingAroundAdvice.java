package org.smart4j.framework.springaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by snow on 2016/4/18.
 * ����֪ͨorg.aopalliance.intercept.MethodInvocation
 * �ýӿڲ���Spring�ṩ�ģ���AOP����д�ã�Springֻ�ǽ�������
 * SpringҲ���Լ��Ļ���֪ͨ�ӿ�org.springframework.cglib.proxy.MethodInterceptor
 * @author openforever
 * @since 1.0.0
 */
public class GreetingAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        before();
        Object result = methodInvocation.proceed();/*�����ķ���ֵ*/
        after();
        return result;
    }

    private void after() {
        System.out.println("after method");
    }

    private void before() {
        System.out.println("before method");
    }
}
