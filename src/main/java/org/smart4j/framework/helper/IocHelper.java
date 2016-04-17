package org.smart4j.framework.helper;


import org.smart4j.framework.annotation.Autowired;
import org.smart4j.framework.utils.ArrayUtil;
import org.smart4j.framework.utils.CollectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by snow on 2016/4/17.
 * ����ע��������
 * Controller��RequestMapping�����е�����Service��Ա�����ķ���
 * ʵ����Service�����ǿ�����ͨ��newʵ��������ͨ�����������ʵ����  IOC(Inversion Of Control)���Ʒ�ת
 *      ���Ʋ����ɿ������������ģ����Ƿ�ת�������
 *      Ҳ�����Ʒ�ת��ΪDI(Dependency Injection,����ע��)
 *      ��ĳ������Ҫ�����ĳ�Ա����ע�뵽�������
 * @author openforever
 * @since 1.0.0
 */
public class IocHelper {

    /*IoC�����ĳ�ʼ��*/
    static {
        /*��ȡ����bean����beanʵ��֮���ӳ���ϵ(���Bean Map)*/
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)){
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()){
                /*��BeanMap�л�ȡBean����Beanʵ��*/
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                /*��ȡBeanClass�����г�Ա���� Bean Field*/
                Field[] fields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(fields)){
                    //����bean Field
                    for (Field beanField : fields){
                        /*�жϵ�ǰbean Field�Ƿ����Autowiredע��*/
                        if (beanField.isAnnotationPresent(Autowired.class)){
                            /*��Bean Map�л�ȡBean Field��Ӧ��ʵ��*/
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null){
                                /*ͨ�������ʼ��BeanField��ֵ*/
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
