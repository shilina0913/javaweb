package setting;

import setting.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 路由映射
 * @author shilina
 */
public class ControllerUtil {

        static Map<String,Handler> controllerMap=new HashMap<String,Handler>(256);
        static {
            Set<Class<?>> classSet = ClassLoadUtil.classSet;
            for (Class<?> cl : classSet) {
                Method[] methods = cl.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        String pathInfo = method.getAnnotation(RequestMapping.class).name();
                        controllerMap.put(pathInfo, new Handler(cl, method));
                    }
                }
            }
        }
}
