package org.smart4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by snow on 2016/4/17.
 * ������������
 * @author openforever
 * @since 1.0.0
 */
public final class StreamUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * ���������л�ȡ�ַ���
     */
    public static String getString(InputStream is){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null ){
                sb.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("get string failure", e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    /**
     * �����������Ƶ������
     */
    public static void copyStream(InputStream inputStream, OutputStream outputStream){
        int length;
        byte[] buffer = new byte[4 * 1024];
        try {
            while ((length = inputStream.read(buffer, 0, buffer.length)) != -1){
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
        } catch (IOException e) {
            LOGGER.error("copy stream failure", e);
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                LOGGER.error("close stream failure", e);
            }
        }
    }
}
