package org.smart4j.framework.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by snow on 2016/4/16.
 * 对Apache Commons做一个简单的封装
 * 集合工具类
 */
public class CollectionUtil {
    /*判断collection是否为空*/
    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }

    /*判断collection是否非空*/
    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }

    /*判断Map是否为空*/
    public static boolean isEmpty(Map<?, ?> map){
        return MapUtils.isEmpty(map);
    }

    /*判断Map是否非空*/
    public static boolean isNotEmpty(Map<?, ?> map){
        return !isEmpty(map);
    }
}
