package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/17.
 * 封装RequestMapping信息
 */
public class Handler {

    /*controller类*/
    private Class<?> controllerClass;

    /*requestMapping方法*/
    private Method requestMappingMethod;

    public Handler(Class<?> controllerClass, Method requestMappingMethod) {
        this.controllerClass = controllerClass;
        this.requestMappingMethod = requestMappingMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getRequestMappingMethod() {
        return requestMappingMethod;
    }
}
