package sbox.learn.unit1.mvc.infra;

import org.apache.log4j.Logger;
import sbox.learn.unit1.mvc.domain.entities.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class BookRepository implements ProjectRepository<Book,Integer> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean remove(Predicate<Book> predicate) {
        logger.info("removing books... ");
        return repo.removeIf(predicate);
    }

    @Override
    public List<Book> find(Predicate<Book> predicate) {
        logger.info("search books... ");
        return repo.stream().filter(predicate).collect(Collectors.toList());
    }
}
