package domain.XmlAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface XmlAttribute {

  public String tag() default "";

  public String name() default "";

}
