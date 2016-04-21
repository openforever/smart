package org.smart4j.framework.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by snow on 2016/4/20.
 */
public class MyThreadLocal<T> {

    /*默认容量都是16，1 << 4 自带的ThreadLocal也是, 以当前线程作为key  使用同步Map*/
    private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set(T value){
        container.put(Thread.currentThread(), value);
    }

    public T get(){
        Thread thread = Thread.currentThread();
        T value = container.get(thread);
        if (value == null && !container.containsKey(thread)){
            value = initialValue();
            container.put(thread, value);
        }
        return value;
    }

    public void remove(){
        container.remove(Thread.currentThread());
    }

    private T initialValue() {
        return null;
    }
}
