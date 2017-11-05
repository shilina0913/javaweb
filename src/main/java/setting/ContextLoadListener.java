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
        ClassLoadUtil classLoadUtil = new ClassLoadUtil();
        classLoadUtil.getClassload();
        //加载指定路径下的class文件
        classLoadUtil.loadClass("business");
        //生成IOC
        BeanCreateUtil beanCreateUtil=new BeanCreateUtil();
        //生成路由映射表
        ControllerUtil controllerUtil=new ControllerUtil();
        System.out.println(BeanCreateUtil.map.isEmpty());
    }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
