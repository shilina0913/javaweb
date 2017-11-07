package setting.annotation;

import java.lang.annotation.*;

/**
 * ${DESCRIPTION}
 *
 * @author shilina
 * @create 2017年11月06日
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    Class<? extends Annotation> value();
}
