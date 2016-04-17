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
 * ������������
 * ��ȡRequestMappingע���е�������ʽ��������ȡ���󷽷�������·������װ�������Request�봦�����Handler
 * ���Request��Handler����һ��ӳ���ϵ������һ��RequestMapping Map��
 * �ṩһ���ɸ������󷽷�������·����ȡ�������ķ���
 * @author openforever
 * @since 1.0.0
 */
public final class ControllerHelper {

    /**
     * ��������봦������ӳ���ϵ(���ActionMap)
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        /*��ȡ���е�Controller��*/
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)){
            /*������ЩController��*/
            for (Class<?> controllerClass : controllerClassSet){
                /*��ȡController���ж�������з���*/
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)){
                    /*������ЩController���еķ���*/
                    for (Method method : methods){
                        /*�жϵ�ǰ�����Ƿ���RequestMappingע������*/
                        if (method.isAnnotationPresent(RequestMapping.class)){
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String mapping = requestMapping.value();
                            /*��֤URLӳ�����*/
                            if (mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2){
                                    /*��ȡ���󷽷�������·��*/
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    /*��ʼ��ACTION_MAP*/
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
