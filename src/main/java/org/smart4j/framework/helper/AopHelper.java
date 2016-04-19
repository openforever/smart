package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by snow on 2016/4/19.
 * ��ȡ���е�Ŀ���༰�䱻���ص�������ʵ��
 * ͨ��ProxyFactory.createProxy�������������������������Bean Map��
 */
public final class AopHelper {

    /**
     * ��ȡAspectע�������õ�ע���࣬���ظ�ע�����ε�������set����
     * @param aspect
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect){
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = aspect.value();/*value����Ϊע������*/
        if (annotation != null && !annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    /**
     * һ��������(����ָ������)���Զ�Ӧһ�����߶��Ŀ����
     * ��������Ҫ��չAspectProxy�����࣬����Ҫ����Aspectע�⣬���ܸ���Aspectע���ж����ע������ȥ��ȡע������Ӧ��Ŀ���༯��
     * @return ���ش�������Ŀ���༯�ϵ�ӳ���ϵ
     */
    private static Map<Class<?>, Set<Class<?>>> createProxyMap(){
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        /*��ȡ������������������*/
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> proxyClass : proxyClassSet){
            if (proxyClass.isAnnotationPresent(Aspect.class)){
                /*��ȡ�Ӵ������Aspectע��*/
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                /*��ȡAspectע�������õ�ע���࣬���ظ�ע�����ε�������set����*/
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass, targetClassSet);
            }
        }
        return proxyMap;
    }
}
