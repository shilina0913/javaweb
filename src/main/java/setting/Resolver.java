package setting;

import com.alibaba.fastjson.JSON;
import setting.annotation.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 视图解析器
 * @author
 */
public class Resolver {

    /**
     *返回jsp视图
     */
    public void viewResolver(ModelAndView view, HttpServletResponse res){
        String viewName=view.getViewName();
        String path="/view/"+viewName+".jsp";
        try {
            res.sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *返回json字符串
     */
    public void dataResolver(Object result,HttpServletResponse res){
       String resultStr= JSON.toJSONString(result);
        try {
            PrintWriter out=res.getWriter();
            out.write(resultStr);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
