package org.smart4j.framework.bean;

import org.smart4j.framework.utils.CastUtil;
import org.smart4j.framework.utils.CollectionUtil;
import org.smart4j.framework.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by snow on 2016/4/17.
 * �����������
 * ���ݲ�������ȡָ�����͵Ĳ���ֵ��Ҳ���Ի�ȡ���в�����Map�ṹ
 * @author openforever
 * @since 1.0.0
 */
public class Param {

    /*һ�����У����еĲ����ɷ�Ϊ���ࣺ���������ļ�����*/
    private List<FormParam> formParams;
    private List<FileParam> fileParams;
    private Map<String, Object> paramMap;/* ������Ӧ��ɾ��@Todo*/

    public Param(Map<String, Object> paramMap){
        this.paramMap = paramMap;
    }

    public Param(List<FormParam> formParams) {
        this.formParams = formParams;
    }

    public Param(List<FormParam> formParams, List<FileParam> fileParams) {
        this.formParams = formParams;
        this.fileParams = fileParams;/*�����ļ�����һ�����ڱ�����*/
    }

    /**
     * ��ȡ���б��������ӳ��
     */
    public Map<String, Object> getFieldMap(){
        Map<String, Object> fieldMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(formParams)){
            for (FormParam formParam :formParams){
                String fieldName = formParam.getFieldName();
                Object fieldValue = formParam.getFieldValue();
                /*���п����ж����ͬ��fieldName������value��ͬ, ʹ������ķָ����ָ�*/
                if (fieldMap.containsKey(fieldName)){
                    fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
                }
                fieldMap.put(fieldName, fieldValue);
            }
        }
        return fieldMap;
    }

    /**
     * ��ȡ�����ϴ��ļ�ӳ��
     * ӳ���ϵû������ @Todo
     * һ���ļ����ֶ�����Ӧһ���ļ��б�
     */
    public Map<String, List<FileParam>> getFileMap(){
        Map<String, List<FileParam>> fileMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(fileParams)){
            for (FileParam fileParam : fileParams){
                /*��ȡ�ļ������ֶ���*/
                String fieldName = fileParam.getFieldName();
                List<FileParam> fileParamList;
                if (fileMap.containsKey(fieldName)){
                    fileParamList = fileMap.get(fieldName);
                } else {
                    fileParamList = new ArrayList<>();
                }
                fileParamList.add(fileParam);
                fileMap.put(fieldName, fileParamList);
            }
        }
        return fileMap;
    }

    /**
     * ��ȡ���е��ϴ��ļ�
     */
    public List<FileParam> getFileList(String fieldName){
        return getFileMap().get(fieldName);
    }

    /**
     * ��ȡΨһ�ϴ����ļ�,ֻ�ϴ���һ��
     */
    public FileParam getFile(String fileName){
        List<FileParam> fileParamList = getFileList(fileName);
        if (CollectionUtil.isNotEmpty(fileParamList) && fileParamList.size() == 1){
            return fileParamList.get(0);
        }
        return null;
    }

    /**
     * ���ݲ�������ȡString�Ͳ���ֵ
     */
    public String getString(String name){
        return CastUtil.castString(getFieldMap().get(name));
    }

    /**
     * ���ݲ�������ȡdouble�Ͳ���ֵ
     */
    public Double getDouble(String name){
        return CastUtil.castDouble(getFieldMap().get(name));
    }

    /**
     * ���ݲ�������ȡLong�Ͳ���ֵ
     */
    public long getLong(String name){
        return CastUtil.castLong(getFieldMap().get(name));
    }

    /**
     * ���ݲ�������ȡLong�Ͳ���ֵ
     */
    public int getInt(String name){
        return CastUtil.castInt(getFieldMap().get(name));
    }

    /**
     * ���ݲ�������ȡLong�Ͳ���ֵ
     */
    public boolean getBoolean(String name){
        return CastUtil.castBoolean(getFieldMap().get(name));
    }

    /**
     * ��ȡ�����ֶ���Ϣ
     */
    /*public Map<String, Object> getMap(){
        return paramMap;
    }*/

    /**
     * ��֤�����Ƿ�Ϊ��
     */
    public boolean isEmpty(){
        return CollectionUtil.isEmpty(formParams) && CollectionUtil.isEmpty(fileParams);
    }
}
