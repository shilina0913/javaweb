package setting;

import setting.annotation.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 视图解析器
 * @author
 */
public class ViewResolver {

    public void resolver(ModelAndView view, HttpServletResponse res){
        String viewName=view.getViewName();
        String path="/view/"+viewName+".jsp";
        try {
            res.sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
