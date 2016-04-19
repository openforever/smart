package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snow on 2016/4/19.
 * 代理链
 * @author openforever
 * @since 1.0.0
 */
public class ProxyChain {

    private final Class<?> targetClass;/*目标类*/
    private final Object targetObject;/*目标对象*/
    private final Method targetMethod;/*目标方法*/
    private final MethodProxy methodProxy;/*方法代理, CGLib提供的方法代理对象*/
    private final Object[] methodParams;/*方法参数*/

    private List<Proxy> proxyList = new ArrayList<Proxy>();/*代理列表*/

    private int proxyIndex = 0;/*代理索引*/

    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod,
                      MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList) {
        this.proxyList = proxyList;
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    /**
     * 会一直调用当前ProxyChain对象的doProxyChain方法，直到proxyIndex达到proxyList上限为止
     * 最后调用methodProxy.invokeSuper，执行目标对象的业务逻辑
     * @return
     * @throws Throwable
     */
    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if (proxyIndex < proxyList.size()){
            methodResult = proxyList.get(proxyIndex++).doProxy(this);/*this*/
        } else {
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }
}
