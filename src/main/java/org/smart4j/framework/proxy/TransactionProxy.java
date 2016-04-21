package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.helper.DBHelper;

import java.lang.reflect.Method;

/**
 * Created by snow on 2016/4/21.
 * �������
 * @author openforever
 * @since 1.0.0
 */
public class TransactionProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

    /*��֤ͬһ�߳��У������������߼�ֻ��ִ��һ��*/
    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag = FLAG_HOLDER.get();
        Method method = proxyChain.getTargetMethod();
        if (!flag && method.isAnnotationPresent(Transaction.class)){
            FLAG_HOLDER.set(true);
            try {
                DBHelper.beginTransaction();
                LOGGER.debug("begin transaction");
                /*ִ��Ŀ�귽��*/
                result = proxyChain.doProxyChain();
                DBHelper.commitTransaction();
                LOGGER.debug("commit transaction");
            } catch (Throwable throwable) {
                DBHelper.rollbackTransaction();
                LOGGER.debug("rollback transaction");
                throw throwable;
            } finally {
                FLAG_HOLDER.remove();
            }
        }else {
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}
