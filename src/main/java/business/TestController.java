package business;

import setting.annotation.Controller;
import setting.annotation.ModelAndView;
import setting.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * ${DESCRIPTION}
 *
 * @author shilina
 * @create 2017年11月03日
 */
@Controller
public class TestController {

    @RequestMapping(name="test")
    public ModelAndView test(HttpServletRequest req){
        return new ModelAndView("test");
    }
}
