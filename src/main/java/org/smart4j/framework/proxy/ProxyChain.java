package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snow on 2016/4/19.
 * ������
 * @author openforever
 * @since 1.0.0
 */
public class ProxyChain {

    private final Class<?> targetClass;/*Ŀ����*/
    private final Object targetObject;/*Ŀ�����*/
    private final Method targetMethod;/*Ŀ�귽��*/
    private final MethodProxy methodProxy;/*��������, CGLib�ṩ�ķ����������*/
    private final Object[] methodParams;/*��������*/

    private List<Proxy> proxyList = new ArrayList<Proxy>();/*�����б�*/

    private int proxyIndex = 0;/*��������*/

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
     * ��һֱ���õ�ǰProxyChain�����doProxyChain������ֱ��proxyIndex�ﵽproxyList����Ϊֹ
     * ������methodProxy.invokeSuper��ִ��Ŀ������ҵ���߼�
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
