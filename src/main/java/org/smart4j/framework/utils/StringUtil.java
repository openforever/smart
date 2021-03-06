package org.smart4j.framework.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by snow on 2016/4/16.
 * 字符串工具类
 */
public class StringUtil {

    /*字符串分隔符  空格 */
    public static final String SEPARATOR = String.valueOf((char) 29);
    public static void main(String[] args){
        System.out.println((char) 29);
    }

    /*判断字符串是否为空,并去掉字符串末尾空白符*/
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /*判断字符串是否非空,并去掉字符串末尾空白符*/
    public static boolean isEmpty(String str){
        if (str != null){
            str = str.trim();
        }
        return StringUtils.isNotEmpty(str);
    }

    public static String[] splitString(String str, String delimiter) {
        return str.split(delimiter);
    }
}
