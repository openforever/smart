package org.smart4j.framework.threadlocal;

/**
 * Created by snow on 2016/4/20.
 */
public class SequenceB implements Sequence {

    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;/*默认值为null，重写一下,protected修饰*/
        }
    };

    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get() + 1);
        return numberContainer.get();
    }

    public static void main(String[] args){
        Sequence sequence = new SequenceB();
        for (int i = 0; i < 3; i++){
            /*每个线程的序列号都从1开始，并且相互不干扰，没有被共享，每个线程各一份,线程安全*/
            new ClientThread(sequence).start();
        }
    }
}
