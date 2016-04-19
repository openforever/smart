package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by snow on 2016/4/19.
 * 代理工厂:创建所有的代理对象
 * 切面类来调用代理工厂，切面类中，需要在目标方法被调用的前后增加相应的逻辑
 * @author openforever
 * @since 1.0.0
 */
public class ProxyFactory {

    /**
     * 提供一个创建代理对象的方法，输入一个目标类和一组Proxy接口实现，输出一个代理对象
     * 使用CGLib提供的Enhance.create方法创建代理对象，将intercept的参数传入ProxyChain的构造器即可
     * @param targetClass
     * @param proxyList
     * @param <T>
     * @return 代理目标类实现了代理接口的代理类
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
