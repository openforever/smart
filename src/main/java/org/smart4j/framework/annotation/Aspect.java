package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by snow on 2016/4/19.
 * ����ע��
 * @author openforever
 * @since 1.0.0
 */
@Target(ElementType.TYPE)/*Ӧ�����ࡢ�ӿ��ϵ�*/
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * value����Ϊע������
     */
    Class<? extends Annotation> value();
}
