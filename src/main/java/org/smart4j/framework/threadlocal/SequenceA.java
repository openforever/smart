package org.smart4j.framework.threadlocal;

/**
 * Created by snow on 2016/4/20.
 */
public class SequenceA implements Sequence {

    private static int number = 0;/*static����ֻ��ʼ��һ��*/
    @Override
    public int getNumber() {
        return ++number;
    }

    public static void main(String[] args){
        /*idea����һ���������࣬��Ȼ�������������Ŀ*/
        Sequence sequence = new SequenceA();
        /*����̹߳���ͬһ�����к�������,�����߳�, �̲߳���ȫ�����ظ������ֵ�*/
        for (int i = 0; i < 3; i++){
            new ClientThread(sequence).start();
        }
    }
}
