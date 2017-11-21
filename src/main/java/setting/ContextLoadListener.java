package setting;

import setting.ioc.BeanCreateUtil;
import setting.ioc.ClassLoadUtil;
import setting.ioc.ControllerUtil;

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
        ClassLoadUtil.getClassload();
        //加载指定路径下的class文件
        ClassLoadUtil.loadClass("business");
        //生成IOC
        BeanCreateUtil beanCreateUtil=new BeanCreateUtil();
        //生成路由映射表
        ControllerUtil controllerUtil=new ControllerUtil();
    }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
