package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.utils.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by snow on 2016/4/17.
 * ClassUtil���ص��඼�ǻ���smart.framework.app.base_package����Ӧ�õĻ�������
 * �ֱ��ȡӦ�ð����µ������ࡢ����Service�ࡢ����Controller��
 * ���Խ�����Controllerע���Serviceע������������Ķ������ΪSmart����������Bean
 * @author openforever
 * @since 1.0.0
 */
public class ClassHelper {
    /**
     * ���弯����(���ڴ�������ص���)
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * ��ȡӦ�ð����µ�������
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * ��ȡӦ�ð����µ�����Service��
     */
    public static Set<Class<?>> getServiceClassSet(){
        return getClassSetByAnnotation(Service.class);
    }

    /**
     * ��ȡӦ�ð����µ�����Controller��
     */
    public static Set<Class<?>> getControllerClassSet(){
        return getClassSetByAnnotation(Controller.class);
    }

    /*��Ҫ��ȡ��չ��AspectProxy����������о����࣬���⣬����Ҫ��ȡ����Aspectע���������
    * instanceof�ж�һ������ʵ���Ƿ���һ�����ӿ�(�������ӽӿ�)��ʵ��  �� --> ��
    * isAssignableFrom �ж���A�ǲ�����B��ͬ��������B�ĸ����ӿ�  �� --> ��
    * */
    /**
     * ��ȡӦ�ð�����ĳ����(��ӿ�)����������(��ʵ����)
     * @param superClass
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> clazz : CLASS_SET){
            /*�ҵ�superClass�������������ʵ����*/
            if (superClass.isAssignableFrom(clazz) && !superClass.equals(clazz)){
                classSet.add(clazz);
            }
        }
        return classSet;
    }

    /**
     * ��ȡӦ�ð����´���ĳע���������
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
     * ��ȡӦ�ð����µ�����Bean��(����Service��Controller)
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }
}
