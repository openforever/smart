package org.smart4j.framework.threadlocal;

/**
 * Created by snow on 2016/4/20.
 * 序列号生成器，需要保证每个线程得到的都是自增的，而不能相互干扰
 */
public interface Sequence {
    int getNumber();
}
