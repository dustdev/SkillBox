package sbox.learn.unit1.mvc.domain.entities;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Book {
    private Integer id;
    @NotBlank(message = "Author should not be empty")
    private String author;
    @NotBlank(message = "Title should not be empty")
    private String title;
    @NotNull(message = "Number of pages should not be empty")
    @Min(value = 1, message = "Number of pages should >0")
    private Integer size;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                '}';
    }
}
