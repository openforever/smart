package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snow on 2016/4/17.
 * 代表JSP视图页面
 * @author openforever
 * @since 1.0.0
 */
public class View {

    /*视图路径*/
    private String path;

    /*模型数据*/
    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        this.model = new HashMap<String, Object>();
    }

    /**
     * 添加模型数据
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
