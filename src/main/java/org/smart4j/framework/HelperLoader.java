package org.smart4j.framework;

import org.smart4j.framework.helper.*;
import org.smart4j.framework.utils.ClassUtil;

/**
 * Created by snow on 2016/4/17.
 * ������Ӧ��Helper��,Ϊ���ü��ظ��Ӽ���
 * @author openforever
 * @since 1.0.0
 */
public class HelperLoader {

    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                /*AopHelperҪ��IocHelper֮ǰ���أ���Ϊ������Ҫͨ��AopHelper��ȡ�������Ȼ�����ͨ��IocHelper��������ע��*/
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> clazz : classList){
            ClassUtil.loadClass(clazz.getName(), true);
        }
    }


}
