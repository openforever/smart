package org.smart4j.framework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by snow on 2016/4/18.
 * 所有的代理类都合并到了JDKDynamicProxy动态代理类中
 * 利用JDK实现动态代理，这里只能代理有接口的类，代理一个没有任何接口的类，可以使用CGlib类库
 * @author openforever
 * @since 1.0.0
 */
public class JDKDynamicProxy implements InvocationHandler {

    /*被代理的目标对象*/
    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);/*result为通过反射执行方法后的返回值*/
        after();
        return result;
    }


    /**
     * 封装了Proxy.newProxyInstance方法(无需传入参数)，使用泛型T，Object向下转型为T，所以IDE有警告
     * @param <T>
     * @return proxy
     */
    @SuppressWarnings("unchecked")
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(), /*类加载器*/
                target.getClass().getInterfaces(), /*该实现类的所有接口*/
                this /*代理target对象的代理类*/
                );
    }

    private void after() {

    }

    private void before() {

    }


}
