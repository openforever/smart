package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/17.
 * 反射工具类，封装Java反射相关的API，提供更好的工具方法
 * @author openforever
 * @since 1.0.0
 */
public class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建指定Class类对象的实例
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
     * 调用方法：调用obj对象的method(args)
     * @param obj
     * @param method
     * @param args
     * @return result
     */
    public static Object invokeMethod(Object obj, Method method, Object ... args){
        Object result;
        try {
            method.setAccessible(true);/*可以访问private修饰的方法*/
            result = method.invoke(obj, args);
        } catch (Exception e) {
            LOGGER.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设定obj对象的field值为value
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value){
        try {
            field.setAccessible(true);/*可以访问private的field*/
            field.set(obj, value);
        } catch (Exception e) {
            LOGGER.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }
}
