package org.smart4j.framework.bean;

import org.smart4j.framework.utils.CastUtil;

import java.util.Map;

/**
 * Created by snow on 2016/4/17.
 * �����������
 * ���ݲ�������ȡָ�����͵Ĳ���ֵ��Ҳ���Ի�ȡ���в�����Map�ṹ
 * @author openforever
 * @since 1.0.0
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * ���ݲ�������ȡLong�Ͳ���ֵ
     */
    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * ��ȡ�����ֶ���Ϣ
     */
    public Map<String, Object> getMap(){
        return paramMap;
    }
}
