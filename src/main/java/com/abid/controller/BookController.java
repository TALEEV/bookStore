package com.abid.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abid.model.Book;
import com.abid.service.BookService;

@RestController
public class BookController {

    private BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/all-books")
    public ResponseEntity<?> getAllBookDetails() {

        final List<Book> books = bookService.getAllBookDetails();
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("No Books Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/book/{bookId}")
    @ResponseBody
    public ResponseEntity<?> getBook(@PathVariable("bookId") final Integer bookId) {
        try {
            final Book book = bookService.getBookDetail(bookId);
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("No Book Detail Found");
        }
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody final Book book) {
        System.out.println("BookName: " + book.getName() + " BookDescription: " + book.getDescription() + " BookAuhtor: " + book.getAuthor()
                + " BookPrice: " + book.getPrice());
        final Book bookResponse = bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") final Integer bookId) {
        try {
            bookService.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("No Book Detail Found");
        }
    }

    @PutMapping("/book")
    public ResponseEntity<?> updateBook(@RequestBody final Book book) {
        try {
            bookService.updateBook(book);
            final Book resultbook = bookService.getBookDetail(book.getIsbn());
            return ResponseEntity.status(HttpStatus.OK).body(resultbook);
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("No Book Detail Found");
        }

    }

    @PostMapping("/checkout")
    public Double checkout(@RequestBody final List<Book> bookList) {
        return bookService.checkout(bookList);
    }

}
