package business;

import setting.annotation.Aspect;
import setting.annotation.Controller;
import setting.aop.AspectProxy;

/**
 * @author shilina
 */
@Aspect(value = Controller.class)
public class TestAspectProxy extends AspectProxy{

    public void before(){
        System.out.println("这是一个前置增强");
    }
}
