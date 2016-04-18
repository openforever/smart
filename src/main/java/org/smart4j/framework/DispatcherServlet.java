package org.smart4j.framework;

import org.smart4j.framework.helper.ConfigHelper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

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




}
