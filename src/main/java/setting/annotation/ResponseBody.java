package setting.annotation;

import java.lang.annotation.*;

/**
 * @author shilina
 */
@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseBody {
}
