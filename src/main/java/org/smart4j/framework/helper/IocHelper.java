package org.smart4j.framework.helper;


import org.smart4j.framework.annotation.Autowired;
import org.smart4j.framework.utils.ArrayUtil;
import org.smart4j.framework.utils.CollectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by snow on 2016/4/17.
 * 依赖注入助手类
 * Controller的RequestMapping方法中调用了Service成员变量的方法
 * 实例化Service：不是开发者通过new实例，而是通过框架自身来实例化  IOC(Inversion Of Control)控制反转
 *      控制不是由开发者来决定的，而是反转给框架了
 *      也将控制反转成为DI(Dependency Injection,依赖注入)
 *      将某个类需要依赖的成员变量注入到这个类中
 * @author openforever
 * @since 1.0.0
 */
public class IocHelper {

    /*IoC容器的初始化*/
    static {
        /*获取所有bean类与bean实例之间的映射关系(简称Bean Map)*/
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)){
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()){
                /*从BeanMap中获取Bean类与Bean实例*/
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                /*获取BeanClass的所有成员变量 Bean Field*/
                Field[] fields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(fields)){
                    //遍历bean Field
                    for (Field beanField : fields){
                        /*判断当前bean Field是否带有Autowired注解*/
                        if (beanField.isAnnotationPresent(Autowired.class)){
                            /*在Bean Map中获取Bean Field对应的实例*/
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null){
                                /*通过反射初始化BeanField的值*/
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
