package setting.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author shilina
 * @create 2017年11月07日
 */
public class ProxyManager {

    public static <T> T createProxy(final Class<?> targerClass, final List<Proxy> proxyList){
        return (T) Enhancer.create(targerClass, new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(o,targerClass,method,objects,proxyList).doProxyChain();
            }
        });
    }
}
