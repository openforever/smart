package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/18.
 * CGLib提供的是方法级别的代理，可以理解为对方法的拦截
 * 与JDK动态代理不同的是，不需要任何接口信息，对谁都可以生成动态代理对象
 * 该对象只需要创建一次，就可以随时使用，使用单例模式
 * Spring,Hibernate等框架也使用了CGLib，运行期间动态生成字节码的工具，也就是动态生成代理类
 * @author openforever
 * @since 1.0.0
 */
public class CGLibProxy implements MethodInterceptor {

    /*饿汉式，加载的时候就产生实例,由JVM保证线程安全*/
    private static CGLibProxy instance = new CGLibProxy();


    private CGLibProxy(){}

    public static CGLibProxy getInstance(){
        return instance;
    }

    /**
     * 返回被代理类clazz的代理类对象
     */
    public <T> T getProxy(Class<T> clazz){
        return (T) Enhancer.create(clazz, this);
    }

    /**
     *CGLib提供的是方法级别的代理MethodProxy
     * @param obj
     * @param method
     * @param args
     * @param proxy 方法代理
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable{
        before();
        Object result = proxy.invokeSuper(obj, args);
        after();
        return result;
    }

    private void after() {

    }

    private void before() {

    }

}
