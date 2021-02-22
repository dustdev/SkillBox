package sbox.learn.unit1.mvc.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import sbox.learn.unit1.mvc.app.services.BookService;
import sbox.learn.unit1.mvc.app.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import sbox.learn.unit1.mvc.common.dto.BookSearchViewModel;

import javax.validation.Valid;

/**
 * Контророллер работы с книгами
 */
@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        model.addAttribute("bookSearch", new BookSearchViewModel());
        model.addAttribute("bookRemove", new BookSearchViewModel());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("bookSearch", new BookSearchViewModel());
            model.addAttribute("bookRemove", new BookSearchViewModel());
            return "book_shelf";
        } else {
            bookService.saveBook(book);
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/remove")
    public Object removeBook(
            @Valid @ModelAttribute("bookRemove") BookSearchViewModel bookRemove,
            BindingResult bindingResult, Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("bookSearch", new BookSearchViewModel());
        model.addAttribute("bookList", bookService.getAllBooks());
        try {
            if (bindingResult.hasErrors()) {
                return "book_shelf";
            } else if (bookService.remove(bookRemove)) {
                return "redirect:/books/shelf";
            } else {
                bindingResult.addError(new ObjectError("bookRemove", "Cannot delete book by specified paramaters"));
                return "book_shelf";
            }
        } catch (Exception ex) {
            model.addAttribute("bookList", bookService.getAllBooks());
            bindingResult.addError(new ObjectError("bookRemove", ex.getMessage()));
            return "book_shelf";
        }
    }

    @PostMapping("/search")
    public Object searchBook(
            @Valid @ModelAttribute("bookSearch") BookSearchViewModel bookSearch,
            BindingResult bindingResult, Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("bookRemove", new BookSearchViewModel());
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("bookList", bookService.getAllBooks());
                return "book_shelf";
            } else {
                model.addAttribute("bookList", bookService.search(bookSearch));
                return "book_shelf";
            }
        } catch (Exception ex) {
            model.addAttribute("bookList", bookService.getAllBooks());
            bindingResult.addError(new ObjectError("bookSearch", ex.getMessage()));
            return "book_shelf";
        }
    }
}
