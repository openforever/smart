package org.smart4j.framework.proxy;

/**
 * Created by snow on 2016/4/18.
 * д�����룬���ô���
 * @author openforever
 * @since 1.0.0
 */
public class DeadNoProxy {
    public static void main(String[] args){
        new GreetingImpl().sayHello("openforever");
    }
}
class GreetingImpl implements Greeting{

    /**
     * before��afterд����ҵ�񷽷����ˣ�����ʹ�����ַ������ᱻ�ᱻ�ܹ�ʦ��Ĺ�Ǻ
     */
    @Override
    public void sayHello(String name) {
        before();
        System.out.println("Hello! " + name);
        after();
    }

    private void after() {
        System.out.println("After");
    }

    private void before() {
        System.out.println("Before");
    }
}
