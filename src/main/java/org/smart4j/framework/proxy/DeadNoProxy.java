package org.smart4j.framework.proxy;

/**
 * Created by snow on 2016/4/18.
 * 写死代码，不用代理
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
     * before和after写死在业务方法中了，大量使用这种方法，会被会被架构师骂的够呛
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
