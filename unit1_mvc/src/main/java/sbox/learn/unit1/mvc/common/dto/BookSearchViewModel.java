package sbox.learn.unit1.mvc.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sbox.learn.unit1.mvc.common.dto.Validators.ValidBookSearch;

/**
 * Модель поиска и удаления книг (входные параметры)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidBookSearch(message = "At least one parameter must be specified")
public class BookSearchViewModel {
    private String id;
    private String author;
    private String title;
    private String size;

    @Override
    public String toString() {
        return "BookSearchViewModel {" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                '}';
    }
}
