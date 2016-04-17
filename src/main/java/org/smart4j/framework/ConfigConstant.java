package org.smart4j.framework;

/**
 * Created by snow on 2016/4/17.
 * 常量类，维护配置文件中相关的配置项名称
 * @author openforever
 * @since 1.0.0
 */
public interface ConfigConstant {
    String CONFIG_FILE = "smart.properties";

    String JDBC_DRIVER = "smart.framework.jdbc.driver";
    String JDBC_URL = "smart.framework.jdbc.url";
    String JDBC_USERNAME = "smart.framework.jdbc.username";
    String JDBC_PASSWORD = "smart.framework.jdbc.password";

    /*基础包名*/
    String APP_BASE_PACKAGE = "smart.framework.app.base_package";
    /*JSP的基础路径*/
    String APP_JSP_PATH = "smart.framework.app.jsp_path";
    /*资源静态文件的基础路径CSS、JS、图片等*/
    String APP_ASSET_PATH = "smart.framework.app.asset_path";
}
