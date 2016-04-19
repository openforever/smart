package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by snow on 2016/4/17.
 * �൱��Bean����, ��ʱһ��beanClass��ֻ��һ��beanʵ��
 * �٣���ȡsmart��ܹ�������е�Bean�࣬ClassHelper.getBeanClassSet()
 * �ڣ�ѭ������ReflectionUtil.newInstance(beanClass)����������ʵ��bean����
 * �ۣ���ÿ�δ�����bean��ŵ�һ����̬��Map<beanClass, bean>��
 * @author openforever
 * @since 1.0.0
 */
public final class BeanHelper {
    /**
     * ����Beanӳ��(���ڴ��Bean����Beanʵ����ӳ���ϵ)
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();/*�������е�Bean Class*/
        for (Class<?> beanClass : beanClassSet){
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
    }

    /**
     * ��ȡbeanӳ��
     * @return BEAN_MAP
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * ����ָ����class�����ȡbeanʵ��
     */
    public static <T> T getBean(Class<T> clazz){
        if (!BEAN_MAP.containsKey(clazz)){
            throw new RuntimeException("can not get bean by class: " + clazz);
        }
        return (T) BEAN_MAP.get(clazz);
    }

    /**
     * ����Beanʵ������bean��ӵ�BEAN_MAP��
     * @param clazz
     * @param obj
     */
    public static void setBean(Class<?> clazz, Object obj){
        BEAN_MAP.put(clazz, obj);
    }
}
