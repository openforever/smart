package org.smart4j.framework.springaop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * Created by snow on 2016/4/18.
 * ���������ǿ
 * ��Ҫ��ǿ����GreetingImpl, ���ڴ�����ֱ��ʵ��Apology�ӿ�,�Ϳ���ͨ������������ʱ����Apology�ӿڵķ���
 * @author openforever
 * @since 1.0.0
 */
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology{

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        return super.invoke(mi);/*ֱ�ӵ����˸����invoke���������Բ���д�������*/
    }

    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! " + name);
    }
}
