package setting;

import setting.annotation.ModelAndView;
import setting.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 前端转发器
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
            //进行视图解析
            if(handler.getMethod().isAnnotationPresent(ResponseBody.class)){
                Resolver resolver=new Resolver();
                resolver.dataResolver(result,res);
                //进行数据解析
            }else{
                Resolver resolver=new Resolver();
                resolver.viewResolver((ModelAndView) result,res);
            }

        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
