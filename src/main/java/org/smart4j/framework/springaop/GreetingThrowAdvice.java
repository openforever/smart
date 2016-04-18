package org.smart4j.framework.springaop;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/18.
 * ��������׳��쳣��һ���Ǵ�ӡ������̨������־�ļ��У������ܶ�ط�����ȥ���������׳��쳣֪ͨ��һ������
 * ThrowsAdvice�̳���AfterAdvice�ӿ�
 * spring aop:�е�����XxxAdvice�ӿڣ���ֻ�Ǳ�ʶ�Խӿڣ�����ʲô��û��
 * @author openforever
 * @since 1.0.0
 */
@Component
public class GreetingThrowAdvice implements ThrowsAdvice{

    /**
     * �ڽӿڷ����п��Ի�ȡ������������Ŀ������쳣�������Ϣ
     * �ĸ�������ĸ�����(�����Ĳ����б�)�׳���ʲô�쳣
     * @param method
     * @param args
     * @param target
     * @param e
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception e){
        System.out.println("-------------Throw Exception-------------");
        System.out.println("Target Class: " + target.getClass().getName());
        System.out.println("Method Name: " + method.getName());
        System.out.println("Exception Message: " + e.getMessage());
        System.out.println("-----------------------------------------");
    }
}
