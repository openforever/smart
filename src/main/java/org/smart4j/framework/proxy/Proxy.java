package org.smart4j.framework.proxy;

/**
 * Created by snow on 2016/4/19.
 * ����ӿ�
 * @author openforever
 * @since 1.0.0
 */
public interface Proxy {
    /**
     * ִ����ʽ����:���������ͨ��һ�����Ӵ�������һ������ȥִ�У�ִ��˳��ȡ������ӵ����ϵ��Ⱥ�˳��
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
