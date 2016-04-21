package org.smart4j.framework.bean;

/**
 * Created by snow on 2016/4/21.
 * 封装表单参数
 * @author openforever
 * @since 1.0.0
 */
public class FormParam  {

    private String fieldName;
    private String fieldValue;

    public FormParam(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
