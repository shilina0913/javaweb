package setting;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * tomcat 服务启动、关闭监听类
 *
 * @author shilina
 * @create 2017年11月03日
 */
public class ContextLoadListener implements ServletContextListener{


    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //实例化单例bean
        ClassLoadUtil classLoadUtil = new ClassLoadUtil();
        classLoadUtil.getClassload();
        classLoadUtil.loadClass("business");
        BeanCreateUtil beanCreateUtil=new BeanCreateUtil();
        System.out.println(BeanCreateUtil.map.isEmpty());
    }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
