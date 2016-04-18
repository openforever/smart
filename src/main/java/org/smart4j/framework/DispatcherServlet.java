package org.smart4j.framework;

import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.ReflectionUtil;
import org.smart4j.framework.utils.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by snow on 2016/4/17.
 * MVC���������ĵ�DispatcherServlet��
 * ����ת����
 * @author openforever
 * @since 1.0.0
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{

    @Override
    public void init(ServletConfig config) throws ServletException {
        /*��ʼ�����Helper��*/
        HelperLoader.init();

        /*��ȡServletContext����(����ע��Servlet)*/
        ServletContext servletContext = config.getServletContext();

        /*ע�ᴦ��JSP��Servlet*/
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        /*ע�ᴦ��̬��Դ��Ĭ��servlet*/
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }


    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*��ȡ���󷽷�������·��*/
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();

        /*��ȡRequestMapping handler������*/
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null){
            /*��ȡcontroller�༰��ʵ��*/
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            /*��ȡ����������Ͷ�Ӧ�Ĳ���ֵ,���������������*/
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()){
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue); //put
            }
            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));

            if (StringUtil.isNotEmpty(body)){
                String[] params = StringUtil.splitString(body ,"&");
                if (ArrayUtil.isNotEmpty(params)){
                    for (String param : params){
                        String[] array = StringUtil.splitString(param, "=");
                        if (ArrayUtil.isNotEmpty(array) && array.length == 2){
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName, paramValue); //put
                        }
                    }
                }
            }

            Param param = new Param(paramMap);
            /*����RequestMapping����*/
            Method requestMappingMethod = handler.getRequestMappingMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, requestMappingMethod, param);

            /*����RequestMapping�����ķ���ֵ*/
            if (result instanceof View){
                /*����JSPҳ��*/
                View view = (View) result;
                String path = view.getPath();
                if (StringUtil.isNotEmpty(path)){
                    if (path.startsWith("/")){
                        resp.sendRedirect(req.getContextPath() + path);
                    }else {
                        Map<String , Object> model = view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()){
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
                    }
                }
            }else if (result instanceof Data){
                /*����JSON����*/
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null){
                    resp.setContentType("application/json");/*��̨���ص���������json*/
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }
}
