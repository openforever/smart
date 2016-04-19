package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by snow on 2016/4/19.
 * ������:�������еĴ������
 * �����������ô��������������У���Ҫ��Ŀ�귽�������õ�ǰ��������Ӧ���߼�
 * @author openforever
 * @since 1.0.0
 */
public class ProxyFactory {

    /**
     * �ṩһ�������������ķ���������һ��Ŀ�����һ��Proxy�ӿ�ʵ�֣����һ���������
     * ʹ��CGLib�ṩ��Enhance.create��������������󣬽�intercept�Ĳ�������ProxyChain�Ĺ���������
     * @param targetClass
     * @param proxyList
     * @param <T>
     * @return ����Ŀ����ʵ���˴���ӿڵĴ�����
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams,
                                    MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList).doProxyChain();
            }
        });
    }
}
