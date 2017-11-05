package setting.annotation;

import java.lang.annotation.*;

/**
 * Created by shilina on 2017/11/4.
 */
@Target({ ElementType.TYPE,ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String name() default "";
}
