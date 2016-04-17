package org.smart4j.framework;

/**
 * Created by snow on 2016/4/17.
 * �����࣬ά�������ļ�����ص�����������
 * @author openforever
 * @since 1.0.0
 */
public interface ConfigConstant {
    String CONFIG_FILE = "smart.properties";

    String JDBC_DRIVER = "smart.framework.jdbc.driver";
    String JDBC_URL = "smart.framework.jdbc.url";
    String JDBC_USERNAME = "smart.framework.jdbc.username";
    String JDBC_PASSWORD = "smart.framework.jdbc.password";

    /*��������*/
    String APP_BASE_PACKAGE = "smart.framework.app.base_package";
    /*JSP�Ļ���·��*/
    String APP_JSP_PATH = "smart.framework.app.jsp_path";
    /*��Դ��̬�ļ��Ļ���·��CSS��JS��ͼƬ��*/
    String APP_ASSET_PATH = "smart.framework.app.asset_path";
}
