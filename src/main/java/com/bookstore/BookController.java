package com.bookstore;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200") //since we’re just working locally
public class BookController {

    BookRepository bookRepository;
    SplitWrapper splitWrapper;

    public BookController(BookRepository bookRepository, SplitWrapper splitWrapper) {
        this.bookRepository = bookRepository;
        this.splitWrapper = splitWrapper;

    }

    @GetMapping("/books/")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable("id") Long id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books/")
    public HttpStatus addBook(@RequestBody Book book){
        bookRepository.save(book);

        return HttpStatus.CREATED;
    }

    @DeleteMapping("/books/{id}")
    public HttpStatus deleteBook(@PathVariable("id") Long id) {
        if (splitWrapper.isTreatmentOn("allow-delete")) {
            bookRepository.deleteById(id);

            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}