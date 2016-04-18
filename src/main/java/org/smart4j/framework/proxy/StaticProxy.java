package org.smart4j.framework.proxy;

/**
 * Created by snow on 2016/4/18.
 * 静态代理，有个问题：每增强一个特定接口方法的功能，就需要定义一个XxxProxy类来实现
 * XxxProxy这样的类会越来越多，最好只有一个代理类(动态代理)
 */
public class StaticProxy {
    public static void main(String[] args){
        new GreetingProxy(new GreetingImpl()).sayHello("snow");
    }
}
class GreetingProxy implements Greeting{
    /**
     * 静态代理，包含一个被代理对象的实例
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
