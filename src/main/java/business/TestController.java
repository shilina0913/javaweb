package business;

import setting.annotation.Controller;
import setting.annotation.ModelAndView;
import setting.annotation.RequestMapping;
import setting.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author shilina
 * @create 2017年11月03日
 */
@Controller
public class TestController {

    @RequestMapping(name="test/view")
    public ModelAndView test(HttpServletRequest req){
        return new ModelAndView("test");
    }

    @RequestMapping(name="test/data")
    @ResponseBody
    public Map data(HttpServletRequest req){
        Map<String,Object> map=new HashMap<String,Object>(4);
        map.put("name","shilina");
        map.put("age","13");
        return map;
    }


}
