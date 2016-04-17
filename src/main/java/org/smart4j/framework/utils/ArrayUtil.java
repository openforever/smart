package org.smart4j.framework.utils;

import org.apache.commons.lang3.ArrayUtils;


/**
 * Created by snow on 2016/4/17.
 * 数组工具类
 * @author openforever
 * @since 1.0.0
 */
public class ArrayUtil {
    /*判断数组是否为空*/
    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }

    /*判断数组是否非空*/
    public static boolean isNotEmpty(Object[] array){
        return !ArrayUtils.isEmpty(array);
    }
}
