package org.smart4j.framework.utils;

import org.apache.commons.lang3.ArrayUtils;


/**
 * Created by snow on 2016/4/17.
 * ���鹤����
 * @author openforever
 * @since 1.0.0
 */
public class ArrayUtil {
    /*�ж������Ƿ�Ϊ��*/
    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }

    /*�ж������Ƿ�ǿ�*/
    public static boolean isNotEmpty(Object[] array){
        return !ArrayUtils.isEmpty(array);
    }
}
