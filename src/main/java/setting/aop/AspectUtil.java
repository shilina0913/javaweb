package setting.aop;

import setting.ioc.BeanCreateUtil;
import setting.ioc.ClassLoadUtil;
import setting.annotation.Aspect;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author shilina
 */
public class AspectUtil {

    public static Map<Class<?>,List<Proxy>> targetMap=new HashMap<Class<?>, List<Proxy>>();

    public static Map<Class<?>,Set<Class<?>>> getProxyMap(){
        Map<Class<?>,Set<Class<?>>> proxyMap=new HashMap<Class<?>,Set<Class<?>>>();
        Set<Class<?>> proxySet=ClassLoadUtil.getClassSetBySuperClass(AspectProxy.class);
        for(Class<?> cl:proxySet){
            Class<? extends Annotation> targetClass=cl.getAnnotation(Aspect.class).value();
            Set<Class<?>> targetSet=ClassLoadUtil.getClassSetByAnnotation(targetClass);
            proxyMap.put(cl,targetSet);
        }
        return proxyMap;
    }

    public static Map<Class<?>,List<Proxy>> getTargetMap() throws IllegalAccessException, InstantiationException {
        Map<Class<?>,Set<Class<?>>> proxyMap=getProxyMap();
        for(Map.Entry<Class<?>,Set<Class<?>>> proxyEntry:proxyMap.entrySet()){
            Set<Class<?>> targetClassSet=proxyEntry.getValue();
            Class<?> proxyClass=proxyEntry.getKey();
            for(Class<?> cl:targetClassSet){
                if(targetMap.get(cl)!=null){
                    targetMap.get(cl).add((Proxy) proxyClass.newInstance());
                }else{
                    List<Proxy> proxyList=new ArrayList<Proxy>();
                    proxyList.add((Proxy)proxyClass.newInstance());
                    targetMap.put(cl,proxyList);
                }
            }
        }
        return targetMap;
    }

    /**
     *将aop特性bean放入bean map中
     */
    public static void setBean(){
        Map<Class<?>,List<Proxy>> targetMap= null;
        try {
            targetMap = getTargetMap();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        for(Map.Entry<Class<?>,List<Proxy>> targetEntry:targetMap.entrySet()){
            Class<?> cl=targetEntry.getKey();
            List<Proxy> proxyList=targetEntry.getValue();
            Object proxy=ProxyManager.createProxy(cl,proxyList);
            BeanCreateUtil.setBean(cl,proxy);
        }
    }




}
