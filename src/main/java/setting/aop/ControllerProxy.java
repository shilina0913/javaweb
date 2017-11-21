package setting.aop;

/**
 * ${DESCRIPTION}
 *
 * @author shilina
 * @create 2017年11月07日
 */
public class ControllerProxy implements Proxy{

    @Override
    public Object doProxy(ProxyChain proxyChain) {
        before();
        Object result=proxyChain.doProxyChain();
        after();
        return result;
    }

    public void before(){
        System.out.println("aop 测试成功");
    }

    public void after(){

    }
}
