package setting;

import setting.annotation.Autowired;
import setting.annotation.Controller;
import setting.annotation.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 实例化单例bean
 *
 * @author shilina
 * @create 2017年09月13日
 */
public class BeanCreateUtil {

    Set<Class<?>> classSet = ClassLoadUtil.classSet;
    public static Map<Class<?>, Object> map = new HashMap<Class<?>, Object>(256);


    public BeanCreateUtil(){
        createBean();
        try {
            injectField();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将含有@Controller、@Service注解的对象放置在map中
     */
    public void createBean() {
        for (Class<?> cl : classSet) {
            if (judge(cl, Controller.class, Service.class)) {
                map.put(cl, createBean(cl));
            }
        }
    }

    /**
     * 遍历hashmap,注入成员变量
     *
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public void injectField() throws IllegalArgumentException, IllegalAccessException {
        Iterator<Map.Entry<Class<?>, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Class<?>, Object> entry = it.next();
            Class<?> cl = entry.getKey();
            Field[] fields = cl.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    setField(map.get(cl), field, map.get(field.getType()));
                }
            }
        }
    }

    /**
     * 设置成员变量的值
     *
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public void setField(Object object, Field field, Object value)
            throws IllegalArgumentException, IllegalAccessException {
        field.setAccessible(true);
        field.set(object, value);
    }

    /**
     * 判断class中是否含有注解
     *
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T> boolean judge(Class<?> cl, Class<?>... classes) {
        for (Class<?> c : classes) {
            if (cl.isAnnotationPresent((Class<? extends Annotation>) c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 通过反射实例化对象
     */
    public static Object createBean(Class<?> cl) {
        try {
            return cl.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }
}
