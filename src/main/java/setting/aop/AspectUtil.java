package setting.aop;

import setting.ClassLoadUtil;
import setting.annotation.Aspect;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * ${DESCRIPTION}
 *
 * @author shilina
 * @create 2017年11月07日
 */
public class AspectUtil {

    public static Map<Class<?>,List<Proxy>> targetMap=new HashMap<Class<?>,List<Proxy>>();

    public static Map<Class<?>,List<Proxy>> getTargetMap(){
        Set<Class<?>> proxySet= ClassLoadUtil.getClassSetBySuperClass(ControllerProxy.class);
        Set<Class<?>> targetSet=ClassLoadUtil.getClassSetByAnnotation(Aspect.class);
        for(Class<?> cl:targetSet){
            //获取切点
            Aspect aspect=cl.getAnnotation(Aspect.class);
            Class<? extends Annotation> targetClass=aspect.value();
            List<Proxy> proxyList=new ArrayList<Proxy>();
            for(Class<?> proxyClass:proxySet){
                try {
                    Proxy proxy= (Proxy) proxyClass.newInstance();
                    proxyList.add(proxy);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            targetMap.put(targetClass,proxyList);
        }
        return targetMap;
    }




}
