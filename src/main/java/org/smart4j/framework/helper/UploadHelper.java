package org.smart4j.framework.helper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.bean.FileParam;
import org.smart4j.framework.bean.FormParam;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.utils.CollectionUtil;
import org.smart4j.framework.utils.FileUtil;
import org.smart4j.framework.utils.StreamUtil;
import org.smart4j.framework.utils.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by snow on 2016/4/21.
 * �ļ��ϴ������ࣺ����Apache commons��FileUpload���
 * @author openforever
 * @since 1.0.0
 */
public final class UploadHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);

    /**
     * Apache commons FileUpload�ṩ��Servlet�ļ��ϴ�����
     */
    private static ServletFileUpload servletFileUpload;

    public static void init(ServletContext servletContext){
        /*�����ϴ��ļ�����ʱĿ¼���ϴ��ļ����������(�����û���������)*/
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
        int uploadLimit = ConfigHelper.getAppUploadLimit();
        if (uploadLimit != 0){
            servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
        }
    }

    /**
     * �ж������ǲ���multipart����,�Ӷ��жϵ�ǰ�����ǲ����ļ��ϴ�����
     */
    public static boolean isMultipart(HttpServletRequest request){
        return ServletFileUpload.isMultipartContent(request);
    }

    /**
     * �ӵ�ǰ�����д���Param����(����FormParam��FileParam)
     */
    public static Param createParam(HttpServletRequest request){
        List<FormParam> formParamList = new ArrayList<>();
        List<FileParam> fileParamList = new ArrayList<>();
        try {
            /*�����������*/
            Map<String, List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
            if (CollectionUtil.isNotEmpty(fileItemListMap)){
                /*�������������������ʼ��formParamList��fileParamList*/
                for (Map.Entry<String, List<FileItem>> fileItemListEntry : fileItemListMap.entrySet()){
                    String fieldName = fileItemListEntry.getKey();
                    List<FileItem> fileItemList = fileItemListEntry.getValue();
                    if (CollectionUtil.isNotEmpty(fileItemList)){
                       for (FileItem fileItem : fileItemList){
                           /*�ǲ�����ͨ���ֶ�*/
                           if (fileItem.isFormField()){
                               String fieldValue = fileItem.getString("UTF-8");
                               formParamList.add(new FormParam(fieldName, fieldValue));
                           }else {/*����Ϊ�ļ��ϴ��ֶ�*/
                               /*getRealFileName��ȡ�ϴ��ļ�����ʵ�ļ���*/
                               String fileName = FileUtil.getRealFileName(new String(fileItem.getName().getBytes(), "UTF-8"));
                               if (StringUtil.isNotEmpty(fieldName)){
                                   long fileSize = fileItem.getSize();
                                   String contentType = fileItem.getContentType();
                                   InputStream inputStream = fileItem.getInputStream();
                                   fileParamList.add(new FileParam(fieldName, fileName, fileSize, contentType, inputStream));
                               }
                           }
                       }
                    }
                }
            }
        } catch (FileUploadException e) {
            LOGGER.error("create param failure", e);
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("unsupported encoding failure", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOGGER.error("get inputStream failure", e);
            throw new RuntimeException(e);
        }
        return new Param(formParamList, fileParamList);
    }

    /**
     * �ϴ��ļ�
     */
    public static void uploadFile(String basePath, FileParam fileParam){
        try {
            if (fileParam != null){
                String filePath = basePath + fileParam.getFileName();
                FileUtil.createFile(filePath);
                InputStream inputStream = new BufferedInputStream(fileParam.getInputStream());
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                StreamUtil.copyStream(inputStream, outputStream);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("upload file failure", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * �����ϴ��ļ�
     */
    public static void uploadFile(String basePath, List<FileParam> fileParamList){
        try {
            if (CollectionUtil.isNotEmpty(fileParamList)){
                for (FileParam fileParam : fileParamList){
                    uploadFile(basePath, fileParam);
                }
            }
        } catch (Exception e) {
            LOGGER.error("upload file failure", e);
            throw new RuntimeException(e);
        }
    }
}
