package ge.itvet.csvreaderlibrary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IndexInLine {
    int value();
    boolean isDate() default false;
    String dateFormat() default "yyyy-MM-dd";
}
