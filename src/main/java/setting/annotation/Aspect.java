package setting.annotation;

import java.lang.annotation.*;

/**
 *  @author shilina
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    Class<? extends Annotation> value();
}
