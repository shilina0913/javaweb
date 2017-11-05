package setting.annotation;

/**
 * 逻辑视图
 * @author shilina
 */
public class ModelAndView {

    private String viewName;

    public ModelAndView(){
    }

    public ModelAndView(String viewName){
        this.viewName=viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
