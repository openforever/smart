package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by snow on 2016/4/17.
 * 控制器注解:标识控制器
 * @author openforever
 * @since 1.0.0
 */
@Target(ElementType.TYPE)/*可以用于描述类、接口(包括注解类型) 或enum声明*/
@Retention(RetentionPolicy.RUNTIME)/*注解生命周期，运行时保留  SOURCE、ClASS*/
public @interface Controller {

}
