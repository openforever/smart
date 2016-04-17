package org.smart4j.framework;

import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.utils.ClassUtil;

/**
 * Created by snow on 2016/4/17.
 * 加载相应的Helper类,为了让加载更加集中
 * @author openforever
 * @since 1.0.0
 */
public class HelperLoader {

    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> clazz : classList){
            ClassUtil.loadClass(clazz.getName());
        }
    }


}
