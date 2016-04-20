package org.smart4j.framework.threadlocal;

/**
 * Created by snow on 2016/4/20.
 */
public class SequenceB implements Sequence {

    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;/*Ĭ��ֵΪnull����дһ��,protected����*/
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
            /*ÿ���̵߳����кŶ���1��ʼ�������໥�����ţ�û�б�����ÿ���̸߳�һ��,�̰߳�ȫ*/
            new ClientThread(sequence).start();
        }
    }
}
