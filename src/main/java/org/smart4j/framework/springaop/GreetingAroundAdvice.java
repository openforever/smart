package org.smart4j.framework.springaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by snow on 2016/4/18.
 * 环绕通知org.aopalliance.intercept.MethodInvocation
 * 该接口不是Spring提供的，是AOP联盟写得，Spring只是借用了他
 * Spring也有自己的环绕通知接口org.springframework.cglib.proxy.MethodInterceptor
 * @author openforever
 * @since 1.0.0
 */
public class GreetingAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        before();
        Object result = methodInvocation.proceed();/*方法的返回值*/
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
