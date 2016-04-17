package org.smart4j.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by snow on 2016/4/17.
 * ��װ������Ϣ

 * @author openforever
 * @since 1.0.0
 */
public class Request {

    /*���󷽷�*/
    private String requestMethod;

    /*����·��*/
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
    public int hashCode() {/*Apache commons�е�*/
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {/*Apache commons�е�*/
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
