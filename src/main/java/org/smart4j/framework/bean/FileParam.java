package org.smart4j.framework.bean;

import java.io.InputStream;

/**
 * Created by snow on 2016/4/21.
 * 封装上传文件参数
 * @author openforever
 * @since 1.0.0
 */
public class FileParam {

    private String fieldName; /*文件表单的字段名 @Todo*/
    private String fileName;
    private long fileSize;
    private String contentType;/*判断上传文件的Content-Type，可判断文件类型*/
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
