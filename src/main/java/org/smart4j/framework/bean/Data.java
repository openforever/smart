package org.smart4j.framework.bean;

/**
 * Created by snow on 2016/4/17.
 * ���ݶ���JSON
 * ��װһ��Object���͵�ģ�����ݣ���ܻὫ�ö������HttpServletResponse�����У��Ӷ�ֱ�����ֵ�����
 * @author openforever
 * @since 1.0.0
 */
public class Data {

    /*ģ������*/
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
