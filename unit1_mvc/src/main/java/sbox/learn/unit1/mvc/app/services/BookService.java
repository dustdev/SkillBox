package sbox.learn.unit1.mvc.app.services;

import sbox.learn.unit1.mvc.app.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbox.learn.unit1.mvc.app.repositories.GenericRepository;
import sbox.learn.unit1.mvc.common.dto.BookSearchViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class BookService {

    private final GenericRepository<Book,Integer> bookRepo;

    @Autowired
    public BookService(GenericRepository<Book,Integer> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retreiveAll();
    }

    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean remove(BookSearchViewModel bookSearchViewModel) {
        return bookRepo.remove(buildSearchPredicate(bookSearchViewModel));
    }

    public List<Book> search(BookSearchViewModel bookSearchViewModel) {
        return bookRepo.find(buildSearchPredicate(bookSearchViewModel));
    }

    private Predicate<Book> buildSearchPredicate(BookSearchViewModel bookSearchViewModel)
    {
        List<Predicate<Book>> allPredicates = new ArrayList<Predicate<Book>>();
        if (!bookSearchViewModel.getId().isEmpty())
            allPredicates.add(a->a.getId().toString().matches(bookSearchViewModel.getId()));
        if (!bookSearchViewModel.getSize().isEmpty())
            allPredicates.add(a->a.getSize().toString().matches(bookSearchViewModel.getSize()));
        if (!bookSearchViewModel.getTitle().isEmpty())
            allPredicates.add(a->a.getTitle().matches(bookSearchViewModel.getTitle()));
        if (!bookSearchViewModel.getAuthor().isEmpty())
            allPredicates.add(a->a.getAuthor().matches(bookSearchViewModel.getAuthor()));
        return allPredicates.stream().reduce(x->true, Predicate::and);
    }
}
