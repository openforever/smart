package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.utils.ClassUtil;

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
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> clazz : CLASS_SET){
            if (clazz.isAnnotationPresent(Service.class)){
                classSet.add(clazz);
            }
        }
        return classSet;
    }

    /**
     * ��ȡӦ�ð����µ�����Controller��
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> clazz : CLASS_SET){
            if (clazz.isAnnotationPresent(Controller.class)){
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
