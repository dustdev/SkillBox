package sbox.learn.unit1.mvc.common.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BookSearchValidator implements ConstraintValidator<ValidBookSearch, BookSearchViewModel> {

    public void initialize(ValidBookSearch constraintAnnotation) {

    }

    public boolean isValid(BookSearchViewModel bookSearchViewModel, ConstraintValidatorContext context) {
        return !(bookSearchViewModel.getAuthor().isEmpty()
                && bookSearchViewModel.getTitle().isEmpty()
                && bookSearchViewModel.getSize().isEmpty()
                && bookSearchViewModel.getId().isEmpty()
        );
    }

}
