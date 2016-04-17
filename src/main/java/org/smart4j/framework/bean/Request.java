package org.smart4j.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by snow on 2016/4/17.
 * 封装请求信息

 * @author openforever
 * @since 1.0.0
 */
public class Request {

    /*请求方法*/
    private String requestMethod;

    /*请求路径*/
    private String requestPath;

    public Request(String requestMethod, String requestPath){
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    @Override
    public int hashCode() {/*Apache commons中的*/
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {/*Apache commons中的*/
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
