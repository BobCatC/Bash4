package domain.XmlAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface XmlTag {

  public String name() default "";

}
