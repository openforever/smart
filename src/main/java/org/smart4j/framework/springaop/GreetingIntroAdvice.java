package org.smart4j.framework.springaop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * Created by snow on 2016/4/18.
 * 类的引入增强
 * 需要增强的类GreetingImpl, 不在代码中直接实现Apology接口,就可以通过该类在运行时调用Apology接口的方法
 * @author openforever
 * @since 1.0.0
 */
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology{

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        return super.invoke(mi);/*直接调用了父类的invoke方法，可以不重写这个方法*/
    }

    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! " + name);
    }
}
