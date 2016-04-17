package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.RequestMapping;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.utils.ArrayUtil;
import org.smart4j.framework.utils.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by snow on 2016/4/17.
 * 控制器助手类
 * 获取RequestMapping注解中的请求表达式，进而获取请求方法与请求路径，封装请求对象Request与处理对象Handler
 * 最后将Request与Handler建立一个映射关系，放入一个RequestMapping Map中
 * 提供一个可根据请求方法与请求路径获取处理对象的方法
 * @author openforever
 * @since 1.0.0
 */
public final class ControllerHelper {

    /**
     * 存放请求与处理器的映射关系(简称ActionMap)
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        /*获取所有的Controller类*/
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)){
            /*遍历这些Controller类*/
            for (Class<?> controllerClass : controllerClassSet){
                /*获取Controller类中定义的所有方法*/
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)){
                    /*遍历这些Controller类中的方法*/
                    for (Method method : methods){
                        /*判断当前方法是否有RequestMapping注解修饰*/
                        if (method.isAnnotationPresent(RequestMapping.class)){
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String mapping = requestMapping.value();
                            /*验证URL映射规则*/
                            if (mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2){
                                    /*获取请求方法与请求路径*/
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    /*初始化ACTION_MAP*/
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
