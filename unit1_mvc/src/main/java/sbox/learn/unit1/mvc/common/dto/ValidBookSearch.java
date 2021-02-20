package sbox.learn.unit1.mvc.common.dto;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { BookSearchValidator.class })
public @interface ValidBookSearch {
    String message() default "BookSearch parameters are invalid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
