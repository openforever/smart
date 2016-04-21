package org.smart4j.framework.bean;

import java.io.InputStream;

/**
 * Created by snow on 2016/4/21.
 * ��װ�ϴ��ļ�����
 * @author openforever
 * @since 1.0.0
 */
public class FileParam {

    private String fieldName; /*�ļ������ֶ��� @Todo*/
    private String fileName;
    private long fileSize;
    private String contentType;/*�ж��ϴ��ļ���Content-Type�����ж��ļ�����*/
    private InputStream inputStream;

    public FileParam() {}

    public FileParam(String fieldName, String fileName, long fileSize, String contentType, InputStream inputStream) {
        this.fieldName = fieldName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
