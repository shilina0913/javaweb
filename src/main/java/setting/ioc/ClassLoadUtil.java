package setting.ioc;

import com.sun.xml.internal.ws.server.sei.SEIInvokerTube;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;

/**
 * 自定义类加载器，用于加载指定文件下的类
 *
 * @author shilina
 * @create 2017年09月13日
 */
public class ClassLoadUtil{
    public static ClassLoader classLoader;
    public static Set<Class<?>> classSet = new HashSet<Class<?>>();


    /**
     * 获得类加载器
     */
    public static void getClassload() {
      classLoader = Thread.currentThread().getContextClassLoader();

    }


    /**
     *获得某超类的子类class文件
     */
    public static Set getClassSetBySuperClass(Class<?> superClass){
        Set<Class<?>> subClassSet=new HashSet<Class<?>>();
        for(Class<?> cl:classSet){
            if(cl.isAssignableFrom(superClass)){
                subClassSet.add(cl);
            }
        }
        return subClassSet;
    }

    /**
     * 获得某注解下的class文件
     */
    public static Set getClassSetByAnnotation(Class<? extends Annotation> annotation){
        Set<Class<?>> annotationClassSet=new HashSet<Class<?>>();
        for(Class<?> cl:classSet){
            if(cl.isAnnotationPresent(annotation)){
                annotationClassSet.add(cl);
            }
        }
        return annotationClassSet;
    }


    /**
     * 获得字节码文件
     */
    public static Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     *获得指定注解的class
     */
    public static Set getClassSetByAnnotation(Class<? extends Annotation> annotation){
        Set<Class<?>> set=new HashSet<Class<?>>();
        for(Class cl:classSet){
            if(cl.isAnnotationPresent(annotation)){
                set.add(cl);
            }
        }
        return set;
    }

    /**
     *获得指定父类的子类
     */
    public static Set getClassSetBySuperClass(Class<?> c){
        Set<Class<?>> set=new HashSet<Class<?>>();
        for(Class cl:classSet){
            if(c.isAssignableFrom(cl)){
                set.add(cl);
            }
        }
        return set;
    }


    public static List<String> loadClass(String path) {
        List<String> list = new ArrayList<String>();
        // 资源定位
        try {
            Enumeration<URL> urls = classLoader.getResources(path);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                // 假设包内均为class文件
                if (url.getProtocol().equals("file")) {
                    addClass(url.getPath(), path);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将指定位置的class文件存放至set里
     */
    public static void addClass(String path, String packageName) {
        File[] files = new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                // TODO Auto-generated method stub
                if ((file.isFile() && file.getName().endsWith(".class")) || file.isDirectory()) {
                    return true;
                }
                return false;
            }
        });
        for (File file : files) {
            if (file.isFile()) {
                // 类的全限定名包括包名
                String className = packageName + "." + file.getName().substring(0, file.getName().indexOf(".class"));
                classSet.add(getClass(className));
            } else if (file.isDirectory()) {
                addClass(path + "/" + file.getName(), packageName + "." + file.getName());
            }
        }
    }
}
