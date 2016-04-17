package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snow on 2016/4/17.
 * ����JSP��ͼҳ��
 * @author openforever
 * @since 1.0.0
 */
public class View {

    /*��ͼ·��*/
    private String path;

    /*ģ������*/
    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        this.model = new HashMap<String, Object>();
    }

    /**
     * ���ģ������
     * @param key
     * @param value
     * @return this
     */
    public View addModel(String key, Object value){
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
