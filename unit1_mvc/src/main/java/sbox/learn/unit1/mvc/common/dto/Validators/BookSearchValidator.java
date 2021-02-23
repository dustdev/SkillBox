package sbox.learn.unit1.mvc.common.dto.Validators;

import sbox.learn.unit1.mvc.common.dto.BookSearchViewModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Кастомный композитный валидатор ViewModel параметров поиска или удаления книг
 */
public class BookSearchValidator implements ConstraintValidator<ValidBookSearch, BookSearchViewModel> {

    public void initialize(ValidBookSearch constraintAnnotation) {

    }

    /**
     * Реализация валидации: проверка что хотя бы одно значение заполнено
     *
     * @param bookSearchViewModel
     * @param context
     * @return
     */
    public boolean isValid(BookSearchViewModel bookSearchViewModel, ConstraintValidatorContext context) {
        return !(bookSearchViewModel.getAuthor().isEmpty()
                && bookSearchViewModel.getTitle().isEmpty()
                && bookSearchViewModel.getSize().isEmpty()
                && bookSearchViewModel.getId()==null
        );
    }

}
