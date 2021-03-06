package setting.annotation;

import java.lang.annotation.*;

/**
 * 路由映射注解
 * @author shilina
 */
@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String name() default "";
}
