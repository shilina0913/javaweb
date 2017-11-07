package setting;

import setting.aop.AspectUtil;
import setting.aop.Proxy;
import setting.aop.ProxyManager;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shilina
 */
public class HandAdapter {

    public static Object handle(Handler hanler, HttpServletRequest req){
        Class<?> cl=hanler.getClassName();
        Map<Class<?>,List<Proxy>> targetMap= AspectUtil.getTargetMap();
        System.out.println(cl.getAnnotations());
        //判断是否需要进行aop操作
        if(targetMap.containsKey(cl.getAnnotations())){
            List<Proxy> proxyList=targetMap.get(cl.getAnnotations());
            return ProxyManager.createProxy(cl,proxyList);
        }
        Object object=BeanCreateUtil.createBean(cl);
        Method method=hanler.getMethod();
        Map<String,Object> map=new HashMap<String,Object>(16);
        Enumeration params=req.getParameterNames();
       while(params.hasMoreElements()){
           String param=(String)params.nextElement();
           String value=req.getParameter(param);
           map.put(param,value);

       }
        try {
            Object result=method.invoke(object,req);
            return result;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


}
