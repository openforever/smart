package org.smart4j.framework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by snow on 2016/4/18.
 * ���еĴ����඼�ϲ�����JDKDynamicProxy��̬��������
 * ����JDKʵ�ֶ�̬��������ֻ�ܴ����нӿڵ��࣬����һ��û���κνӿڵ��࣬����ʹ��CGlib���
 * @author openforever
 * @since 1.0.0
 */
public class JDKDynamicProxy implements InvocationHandler {

    /*�������Ŀ�����*/
    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);/*resultΪͨ������ִ�з�����ķ���ֵ*/
        after();
        return result;
    }


    /**
     * ��װ��Proxy.newProxyInstance����(���贫�����)��ʹ�÷���T��Object����ת��ΪT������IDE�о���
     * @param <T>
     * @return proxy
     */
    @SuppressWarnings("unchecked")
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(), /*�������*/
                target.getClass().getInterfaces(), /*��ʵ��������нӿ�*/
                this /*����target����Ĵ�����*/
                );
    }

    private void after() {

    }

    private void before() {

    }


}
