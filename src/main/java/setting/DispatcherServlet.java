package setting;

import setting.annotation.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author shilina
 * @create 2017年11月03日
 */
public class DispatcherServlet extends HttpServlet{

    @Override
    public void init(){
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Handler handler=ControllerUtil.controllerMap.get(req.getRequestURI().substring(1,req.getRequestURI().length()));
        if(handler!=null){
            Object result=HandAdapter.handle(handler,req);
            ViewResolver resolver=new ViewResolver();
            resolver.resolver((ModelAndView) result,res);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
