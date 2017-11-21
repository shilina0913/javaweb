package setting;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shilina
 */
public class HandAdapter {

    public static Object handle(Handler hanler, Object object,HttpServletRequest req){
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
