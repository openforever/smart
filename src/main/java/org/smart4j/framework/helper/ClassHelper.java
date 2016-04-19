package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.utils.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by snow on 2016/4/17.
 * ClassUtil加载的类都是基于smart.framework.app.base_package整个应用的基础包名
 * 分别获取应用包名下的所有类、所有Service类、所有Controller类
 * 可以将带有Controller注解和Service注解的类所产生的对象，理解为Smart框架所管理的Bean
 * @author openforever
 * @since 1.0.0
 */
public class ClassHelper {
    /**
     * 定义集合类(用于存放所加载的类)
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包名下的所有类
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取应用包名下的所有Service类
     */
    public static Set<Class<?>> getServiceClassSet(){
        return getClassSetByAnnotation(Service.class);
    }

    /**
     * 获取应用包名下的所有Controller类
     */
    public static Set<Class<?>> getControllerClassSet(){
        return getClassSetByAnnotation(Controller.class);
    }

    /*需要获取扩展了AspectProxy抽象类的所有具体类，此外，还需要获取带有Aspect注解的所有类
    * instanceof判断一个对象实例是否是一个类或接口(或子类子接口)的实例  子 --> 父
    * isAssignableFrom 判断类A是不是类B相同或者是类B的父类或接口  父 --> 子
    * */
    /**
     * 获取应用包名下某父类(或接口)的所有子类(或实现类)
     * @param superClass
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> clazz : CLASS_SET){
            /*找到superClass的所有子类或者实现类*/
            if (superClass.isAssignableFrom(clazz) && !superClass.equals(clazz)){
                classSet.add(clazz);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下带有某注解的所有类
     * @param annotationClass
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> clazz : CLASS_SET){
            if (clazz.isAnnotationPresent(annotationClass)){
                classSet.add(clazz);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下的所有Bean类(包括Service、Controller)
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }
}
