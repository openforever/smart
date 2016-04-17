package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/17.
 * ���乤���࣬��װJava������ص�API���ṩ���õĹ��߷���
 * @author openforever
 * @since 1.0.0
 */
public class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * ����ָ��Class������ʵ��
     * @param clazz
     * @return instance
     */
    public static Object newInstance(Class<?> clazz){
        Object instance;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * ���÷���������obj�����method(args)
     * @param obj
     * @param method
     * @param args
     * @return result
     */
    public static Object invokeMethod(Object obj, Method method, Object ... args){
        Object result;
        try {
            method.setAccessible(true);/*���Է���private���εķ���*/
            result = method.invoke(obj, args);
        } catch (Exception e) {
            LOGGER.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * �趨obj�����fieldֵΪvalue
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value){
        try {
            field.setAccessible(true);/*���Է���private��field*/
            field.set(obj, value);
        } catch (Exception e) {
            LOGGER.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }
}
