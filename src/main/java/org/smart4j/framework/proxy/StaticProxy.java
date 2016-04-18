package org.smart4j.framework.proxy;

/**
 * Created by snow on 2016/4/18.
 * ��̬�����и����⣺ÿ��ǿһ���ض��ӿڷ����Ĺ��ܣ�����Ҫ����һ��XxxProxy����ʵ��
 * XxxProxy���������Խ��Խ�࣬���ֻ��һ��������(��̬����)
 */
public class StaticProxy {
    public static void main(String[] args){
        new GreetingProxy(new GreetingImpl()).sayHello("snow");
    }
}
class GreetingProxy implements Greeting{
    /**
     * ��̬��������һ������������ʵ��
     */
    private GreetingImpl greetingImpl;

    public GreetingProxy(GreetingImpl greetingImpl) {
        this.greetingImpl = greetingImpl;
    }

    @Override
    public void sayHello(String name) {
        before();
        greetingImpl.sayHello(name);
        after();
    }

    private void after() {
        System.out.println("After");
    }

    private void before() {
        System.out.println("Before");
    }
}
