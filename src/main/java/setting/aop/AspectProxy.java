package setting.aop;

/**
 * @author shilina
 */
public class AspectProxy implements Proxy {


    public Object doProxy(ProxyChain proxyChain) {

        before();
        Object result=proxyChain.doProxyChain();
        after();
        return result;
    }

    public void before(){

    }

    public void after(){

    }
}
