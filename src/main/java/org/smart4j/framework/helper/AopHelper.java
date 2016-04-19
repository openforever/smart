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
 * 获取所有的目标类及其被拦截的切面类实例
 * 通过ProxyFactory.createProxy方法来创建代理对象，最后将其放入Bean Map中
 */
public final class AopHelper {

    /**
     * 获取Aspect注解中设置的注解类，返回该注解修饰的所有类set集合
     * @param aspect
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect){
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = aspect.value();/*value属性为注解类型*/
        if (annotation != null && !annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    /**
     * 一个代理类(这里指切面类)可以对应一个或者多个目标类
     * 代理类需要扩展AspectProxy抽象类，还需要带有Aspect注解，才能根据Aspect注解中定义的注解属性去获取注解所对应的目标类集合
     * @return 返回代理类与目标类集合的映射关系
     */
    private static Map<Class<?>, Set<Class<?>>> createProxyMap(){
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        /*获取切面类代理的所有子类*/
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> proxyClass : proxyClassSet){
            if (proxyClass.isAnnotationPresent(Aspect.class)){
                /*获取子代理类的Aspect注解*/
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                /*获取Aspect注解中设置的注解类，返回该注解修饰的所有类set集合*/
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass, targetClassSet);
            }
        }
        return proxyMap;
    }
}
