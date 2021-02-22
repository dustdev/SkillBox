package sbox.learn.unit1.mvc.app.repositories.Impl;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import sbox.learn.unit1.mvc.app.entities.Book;
import org.springframework.stereotype.Repository;
import sbox.learn.unit1.mvc.app.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
@Log4j
public class BookRepositoryImpl implements BookRepository {

    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        log.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean remove(Predicate<Book> predicate) {
        log.info("removing books... ");
        return repo.removeIf(predicate);
    }

    @Override
    public List<Book> find(Predicate<Book> predicate) {
        log.info("search books... ");
        return repo.stream().filter(predicate).collect(Collectors.toList());
    }
}
