package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.proxy.AspectProxy;
import org.smart4j.framework.proxy.Proxy;

import java.lang.annotation.Annotation;
import java.util.*;

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

    /**
     * ���ݴ�������Ŀ���༯��֮���ӳ���ϵ��������Ŀ�������������б�֮���ӳ���ϵ
     * @param proxyMap
     * @return
     */
    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap)
            throws IllegalAccessException, InstantiationException {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()){
            /*һ���������Ӧһ��Ŀ�꼯��*/
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();/*Ŀ���༯��*/
            for (Class<?> targetClass : targetClassSet){
                /*��ȡ���������*/
                Proxy proxy = (Proxy) proxyClass.newInstance();
                /*һ��Ŀ�����Ӧһ������������б�����Ϊʲô��List������Set*/
                if (targetMap.containsKey(targetClass)){
                    targetMap.get(targetClass).add(proxy);
                }else {
                    List<Proxy> proxyList = new ArrayList<Proxy>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass, proxyList);
                }
            }
        }
        return targetMap;
    }
}
