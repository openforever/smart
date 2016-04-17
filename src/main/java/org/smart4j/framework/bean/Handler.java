package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/17.
 * ��װRequestMapping��Ϣ
 */
public class Handler {

    /*controller��*/
    private Class<?> controllerClass;

    /*requestMapping����*/
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
