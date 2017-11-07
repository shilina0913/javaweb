package setting.aop;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author shilina
 * @create 2017年11月07日
 */
public class ProxyChain {


    private Object targetObject;
    private Class<?> targetClass;
    private Method targetMethod;
    private Object[] methodParams;
    private MethodProxy methodProxy;

    private int proxyIndex=0;

    private List<Proxy> proxyList=new ArrayList<Proxy>();

    public ProxyChain(Object targetObject,Class<?> targetClass,Method targetMethod,Object[] methodParams,List<Proxy> proxyList){
        this.targetObject=targetObject;
        this.targetClass=targetClass;
        this.targetMethod=targetMethod;
        this.methodParams=methodParams;
        this.proxyList=proxyList;
    }

    public Object doProxyChain(){
        Object result=null;
        if(proxyIndex<proxyList.size()){
            result=proxyList.get(proxyIndex++).doProxy(this);
        }else{
            try {
                result=methodProxy.invokeSuper(targetObject,methodParams);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return result;
    }



}
