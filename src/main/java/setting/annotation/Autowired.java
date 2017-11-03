package setting.annotation;

import java.lang.annotation.*;

/**
 * 属性注入的注解
 *
 * @author shilina
 * @create 2017年09月13日
 */
@Target({ ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {


}
