package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by snow on 2016/4/17.
 * ������ע��:��ʶ������
 * @author openforever
 * @since 1.0.0
 */
@Target(ElementType.TYPE)/*�������������ࡢ�ӿ�(����ע������) ��enum����*/
@Retention(RetentionPolicy.RUNTIME)/*ע���������ڣ�����ʱ����  SOURCE��ClASS*/
public @interface Controller {

}
