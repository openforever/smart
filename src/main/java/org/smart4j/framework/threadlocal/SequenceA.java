package org.smart4j.framework.threadlocal;

/**
 * Created by snow on 2016/4/20.
 */
public class SequenceA implements Sequence {

    private static int number = 0;/*static变量只初始化一次*/
    @Override
    public int getNumber() {
        return ++number;
    }

    public static void main(String[] args){
        /*idea运行一个单独的类，居然都会编译整个项目*/
        Sequence sequence = new SequenceA();
        /*多个线程共享同一个序列号生成器,创建线程, 线程不安全，有重复的数字等*/
        for (int i = 0; i < 3; i++){
            new ClientThread(sequence).start();
        }
    }
}
