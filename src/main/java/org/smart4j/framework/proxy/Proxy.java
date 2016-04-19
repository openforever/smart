package org.smart4j.framework.proxy;

/**
 * Created by snow on 2016/4/19.
 * 代理接口
 * @author openforever
 * @since 1.0.0
 */
public interface Proxy {
    /**
     * 执行链式代理:将多个代理通过一条链子串起来，一个个的去执行，执行顺序取决于添加到链上的先后顺序
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
