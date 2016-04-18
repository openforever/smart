package org.smart4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by snow on 2016/4/18.
 * 编码解码工具类
 * @author openforever
 * @since 1.0.0
 */
public class CodecUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将URL以UTF-8为编码集编码
     */
    public static String encodeURL(String source){
        String target = null;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("encode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将URL以UTF-8为字符集解码
     */
    public static String decodeURL(String source){
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LOGGER.error("decode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
