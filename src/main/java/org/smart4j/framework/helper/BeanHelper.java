package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by snow on 2016/4/17.
 * 相当于Bean容器, 此时一个beanClass类只有一个bean实例
 * ①：获取smart框架管理的所有的Bean类，ClassHelper.getBeanClassSet()
 * ②：循环调用ReflectionUtil.newInstance(beanClass)，根据类来实例bean对象
 * ③：将每次创建的bean存放到一个静态的Map<beanClass, bean>中
 * @author openforever
 * @since 1.0.0
 */
public final class BeanHelper {
    /**
     * 定义Bean映射(用于存放Bean类与Bean实例的映射关系)
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();/*返回所有的Bean Class*/
        for (Class<?> beanClass : beanClassSet){
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
    }

    /**
     * 获取bean映射
     * @return BEAN_MAP
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 根据指定的class对象获取bean实例
     */
    public static <T> T getBean(Class<T> clazz){
        if (!BEAN_MAP.containsKey(clazz)){
            throw new RuntimeException("can not get bean by class: " + clazz);
        }
        return (T) BEAN_MAP.get(clazz);
    }

    /**
     * 设置Bean实例，将bean添加到BEAN_MAP中
     * @param clazz
     * @param obj
     */
    public static void setBean(Class<?> clazz, Object obj){
        BEAN_MAP.put(clazz, obj);
    }
}
