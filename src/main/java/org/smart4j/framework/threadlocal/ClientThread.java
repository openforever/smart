package org.smart4j.framework.threadlocal;

/**
 * Created by snow on 2016/4/20.
 */
public class ClientThread extends Thread {
    /*ÿ���̶߳���һ�����к�����������*/
    private Sequence sequence;

    public ClientThread(Sequence sequence){
        this.sequence = sequence;
    }


    public void run(){
        for (int i = 0; i < 3; i++){
            System.out.println(/*Thread.currentThread()*/this.getName() + " => " + sequence.getNumber());
        }
    }
}
