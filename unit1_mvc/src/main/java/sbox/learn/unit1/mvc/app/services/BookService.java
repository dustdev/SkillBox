package sbox.learn.unit1.mvc.app.services;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Visitor;
import com.querydsl.sql.SQLQuery;
import lombok.extern.log4j.Log4j;
import lombok.var;
import org.apache.commons.collections4.IterableUtils;
import sbox.learn.unit1.mvc.app.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbox.learn.unit1.mvc.app.entities.QBook;
import sbox.learn.unit1.mvc.app.repositories.BookRepository;
import sbox.learn.unit1.mvc.common.dto.BookSearchViewModel;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static sbox.learn.unit1.mvc.app.entities.QBook.book;

/**
 * Управление книгами
 */
@Service
@Log4j
public class BookService {

    private final BookRepository bookRepo;

    @Autowired
    public BookService(BookRepository  bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return IterableUtils.toList(bookRepo.findAll());
    }

    public void saveBook(Book book) {
        bookRepo.save(book);
    }

    public boolean remove(BookSearchViewModel bookSearchViewModel) {
        bookRepo.deleteWhere(buildSearchPredicate(bookSearchViewModel));
        return true;
    }

    public List<Book> search(BookSearchViewModel bookSearchViewModel) {
        return IterableUtils.toList(
                bookRepo.findAll(
                        buildSearchPredicate(bookSearchViewModel)
                        ));
    }

    private Predicate buildSearchPredicate(BookSearchViewModel bookSearchViewModel)
    {
        List<Predicate> allPredicates = new ArrayList<Predicate>();

        if (bookSearchViewModel.getId()!=null)
            allPredicates.add(book.id.like(bookSearchViewModel.getId().toString()));
        if (!bookSearchViewModel.getSize().isEmpty())
            allPredicates.add(book.size.like(bookSearchViewModel.getSize()));
        if (!bookSearchViewModel.getTitle().isEmpty())
            allPredicates.add(book.title.like(bookSearchViewModel.getTitle()));
        if (!bookSearchViewModel.getAuthor().isEmpty())
            allPredicates.add(book.author.like(bookSearchViewModel.getAuthor()));
        return ExpressionUtils.allOf(allPredicates);
    }
}
