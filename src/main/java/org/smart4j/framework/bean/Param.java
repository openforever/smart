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
 * 请求参数对象
 * 根据参数名获取指定类型的参数值，也可以获取所有参数的Map结构
 * @author openforever
 * @since 1.0.0
 */
public class Param {

    /*一个表单中，所有的参数可分为两类：表单参数和文件参数*/
    private List<FormParam> formParams;
    private List<FileParam> fileParams;
    private Map<String, Object> paramMap;/* 该属性应该删除@Todo*/

    public Param(Map<String, Object> paramMap){
        this.paramMap = paramMap;
    }

    public Param(List<FormParam> formParams) {
        this.formParams = formParams;
    }

    public Param(List<FormParam> formParams, List<FileParam> fileParams) {
        this.formParams = formParams;
        this.fileParams = fileParams;/*存在文件参数一定存在表单参数*/
    }

    /**
     * 获取所有表单请求参数映射
     */
    public Map<String, Object> getFieldMap(){
        Map<String, Object> fieldMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(formParams)){
            for (FormParam formParam :formParams){
                String fieldName = formParam.getFieldName();
                Object fieldValue = formParam.getFieldValue();
                /*表单中可能有多个相同的fieldName，但是value不同, 使用特殊的分隔符分割*/
                if (fieldMap.containsKey(fieldName)){
                    fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
                }
                fieldMap.put(fieldName, fieldValue);
            }
        }
        return fieldMap;
    }

    /**
     * 获取所有上传文件映射
     * 映射关系没整明白 @Todo
     * 一个文件表单字段名对应一个文件列表
     */
    public Map<String, List<FileParam>> getFileMap(){
        Map<String, List<FileParam>> fileMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(fileParams)){
            for (FileParam fileParam : fileParams){
                /*获取文件表单的字段名*/
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
     * 获取所有的上传文件
     */
    public List<FileParam> getFileList(String fieldName){
        return getFileMap().get(fieldName);
    }

    /**
     * 获取唯一上传的文件,只上传了一个
     */
    public FileParam getFile(String fileName){
        List<FileParam> fileParamList = getFileList(fileName);
        if (CollectionUtil.isNotEmpty(fileParamList) && fileParamList.size() == 1){
            return fileParamList.get(0);
        }
        return null;
    }

    /**
     * 根据参数名获取String型参数值
     */
    public String getString(String name){
        return CastUtil.castString(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取double型参数值
     */
    public Double getDouble(String name){
        return CastUtil.castDouble(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取Long型参数值
     */
    public long getLong(String name){
        return CastUtil.castLong(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取Long型参数值
     */
    public int getInt(String name){
        return CastUtil.castInt(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取Long型参数值
     */
    public boolean getBoolean(String name){
        return CastUtil.castBoolean(getFieldMap().get(name));
    }

    /**
     * 获取所有字段信息
     */
    /*public Map<String, Object> getMap(){
        return paramMap;
    }*/

    /**
     * 验证参数是否为空
     */
    public boolean isEmpty(){
        return CollectionUtil.isEmpty(formParams) && CollectionUtil.isEmpty(fileParams);
    }
}
