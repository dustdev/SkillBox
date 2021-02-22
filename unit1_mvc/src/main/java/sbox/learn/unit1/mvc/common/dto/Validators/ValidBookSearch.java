package sbox.learn.unit1.mvc.common.dto.Validators;

import sbox.learn.unit1.mvc.common.dto.Validators.BookSearchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннатация валидации ViewModel параметров поиска и удаления книг
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {BookSearchValidator.class})
public @interface ValidBookSearch {
    String message() default "BookSearch parameters are invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
