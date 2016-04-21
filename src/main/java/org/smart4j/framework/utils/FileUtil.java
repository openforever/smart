package org.smart4j.framework.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by snow on 2016/4/21.
 * �ļ�����������
 * @author openforever
 * @since 1.0.0
 */
public final class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * ��ȡ��ʵ�ļ���(�Զ�ȥ���ļ�·��)
     * @param fileName  �ļ�������·��
     */
    public static String getRealFileName(String fileName){
        return FilenameUtils.getName(fileName);
    }

    public static File createFile(String filePath){
        File file/* = null����ġ���׸��*/;
        try {
            file = new File(filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()){
                FileUtils.forceMkdir(parentDir);
            }
        } catch (IOException e) {
            LOGGER.error("create file failure", e);
            throw new RuntimeException(e);
        }
        return file;
    }
}
