package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/18.
 * CGLib�ṩ���Ƿ�������Ĵ����������Ϊ�Է���������
 * ��JDK��̬����ͬ���ǣ�����Ҫ�κνӿ���Ϣ����˭���������ɶ�̬�������
 * �ö���ֻ��Ҫ����һ�Σ��Ϳ�����ʱʹ�ã�ʹ�õ���ģʽ
 * Spring,Hibernate�ȿ��Ҳʹ����CGLib�������ڼ䶯̬�����ֽ���Ĺ��ߣ�Ҳ���Ƕ�̬���ɴ�����
 * @author openforever
 * @since 1.0.0
 */
public class CGLibProxy implements MethodInterceptor {

    /*����ʽ�����ص�ʱ��Ͳ���ʵ��,��JVM��֤�̰߳�ȫ*/
    private static CGLibProxy instance = new CGLibProxy();


    private CGLibProxy(){}

    public static CGLibProxy getInstance(){
        return instance;
    }

    /**
     * ���ر�������clazz�Ĵ��������
     */
    public <T> T getProxy(Class<T> clazz){
        return (T) Enhancer.create(clazz, this);
    }

    /**
     *CGLib�ṩ���Ƿ�������Ĵ���MethodProxy
     * @param obj
     * @param method
     * @param args
     * @param proxy ��������
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable{
        before();
        Object result = proxy.invokeSuper(obj, args);
        after();
        return result;
    }

    private void after() {

    }

    private void before() {

    }

}
