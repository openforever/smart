package org.smart4j.framework.bean;

/**
 * Created by snow on 2016/4/17.
 * 数据对象，JSON
 * 封装一个Object类型的模型数据，框架会将该对象放入HttpServletResponse对象中，从而直接输出值浏览器
 * @author openforever
 * @since 1.0.0
 */
public class Data {

    /*模型数据*/
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
