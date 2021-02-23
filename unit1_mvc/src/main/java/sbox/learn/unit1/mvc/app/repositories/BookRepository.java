package sbox.learn.unit1.mvc.app.repositories;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.stereotype.Repository;
import sbox.learn.unit1.mvc.app.entities.Book;

/**
 * Интерфейс хранилища книг
 */
@Repository
public interface BookRepository extends QuerydslJdbcRepository<Book, Long> {
}
