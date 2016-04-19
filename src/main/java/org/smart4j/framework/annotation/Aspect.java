package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by snow on 2016/4/19.
 * 切面注解
 * @author openforever
 * @since 1.0.0
 */
@Target(ElementType.TYPE)/*应用在类、接口上等*/
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * value属性为注解类型
     */
    Class<? extends Annotation> value();
}
