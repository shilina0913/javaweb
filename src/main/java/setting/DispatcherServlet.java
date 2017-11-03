package setting;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
        System.out.println(req.getPathInfo());
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
