package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/19.
 * 切面代理模版抽象类，提供模版方法，在该抽象类的具体实现中扩展相应的抽象方法
 * @author openforever
 * @since 1.0.0
 */
public abstract class AspectProxy implements Proxy{
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    /**
     * 从proxyChain参数中获取目标类、目标方法与方法参数
     * 通过一个try..catch..finally来实现一个调用框架，从框架中抽象出一系列的"钩子方法"
     * 在AspectProxy的子类中有选择的进行实现
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    @Override
    public final Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        Class<?> clazz = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            if (intercept(clazz, method, params)){
                before(clazz, method, params);
                result = proxyChain.doProxyChain();
                after(clazz, method, params);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            LOGGER.error("proxy failure", e);
            error(clazz, method, params, e);
            throw e;
        } finally {
            end();
        }
        return result;
    }

    public void begin(){}
    public boolean intercept(Class<?> clazz, Method method, Object[] params) throws Throwable{
        return true;
    }
    public void before(Class<?> clazz, Method method, Object[] params) throws Throwable{}
    public void after(Class<?> clazz, Method method, Object[] params) throws Throwable{}
    public void error(Class<?> clazz, Method method, Object[] params, Throwable e) throws Throwable{}
    public void end(){}

}

@Deprecated//AspectProxy抽象类的示例实现
@Aspect(Controller.class)//拦截controller所有方法,统计方法的执行时间
class ControllerAspect extends AspectProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
    private long begin;

    @Override
    public void before(Class<?> clazz, Method method, Object[] params) throws Throwable {
        LOGGER.debug("-------------------begin-----------------");
        LOGGER.debug(String.format("class: %s", clazz.getName()));
        LOGGER.debug(String .format("method: %s", method.getName()));
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> clazz, Method method, Object[] params) throws Throwable {
        LOGGER.debug(String.format("time: %dms", System.currentTimeMillis() - begin));
        LOGGER.debug("--------------------end------------------");
    }
}
