package setting.aop;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行链
 * @author shilina
 */
public class ProxyChain {

    private Object targetObject;
    private Class<?> targetClass;
    private Method targetMethod;
    private MethodProxy methodProxy;
    private Object[] methodParams;
    private List<Proxy> proxyList=new ArrayList<Proxy>();

    public Class<?> getTargetClass(){
        return targetClass;
    }

    public Method getTargetMethod(){
        return targetMethod;
    }

    public Object[] getMethodParams(){
        return methodParams;

    }


    private int proxyIndex=0;

    public ProxyChain(Object targetObject,Class<?> targetClass,Method targetMethod,
                      MethodProxy methodProxy,Object[] methodParams,List<Proxy> proxyList) {
        this.targetObject=targetObject;
        this.targetClass=targetClass;
        this.targetMethod=targetMethod;
        this.methodProxy=methodProxy;
        this.methodParams=methodParams;
        this.proxyList=proxyList;
    }

    public Object doProxyChain(){
        if(proxyIndex<proxyList.size()){
            return proxyList.get(proxyIndex++).doProxy(this);
        }else{
            try {
                return methodProxy.invokeSuper(targetObject,methodParams);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }


}
