package setting.annotation;

import java.lang.annotation.*;

/**
 * 控制层注解
 *
 * @author shilina
 * @create 2017年09月13日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
}
