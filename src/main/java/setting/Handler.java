package setting;

import java.lang.reflect.Method;

/**
 * @author shilina
 */
public class Handler {

    private Class<?> className;
    private Method method;

    public Handler(Class<?> className,Method method){
        this.className=className;
        this.method=method;
    }

    public Class<?> getClassName(){
        return className;
    }

    public Method getMethod(){
        return method;
    }





}
