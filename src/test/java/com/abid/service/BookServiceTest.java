package com.abid.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.abid.model.Book;
import com.abid.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @InjectMocks
    BookRepository bookRepository;
    
    
    @Test
    public void shouldReturnAllBooks() {
        final List<Book> books = new ArrayList();
        books.add(new Book());

        BDDMockito.given(bookRepository.findAll()).willReturn(books);

        final List<Book> expected = bookService.getAllBookDetails();

        Assertions.assertEquals(expected, books);
        Mockito.verify(bookRepository).findAll();
    }

    @Test
    public void shouldReturnSingleBook() {
        final Book book = new Book();

        BDDMockito.given(bookRepository.getReferenceById(1)).willReturn(book);

        final Book expected = bookService.getBookDetail(1);

        Assertions.assertEquals(expected, book);
        Mockito.verify(bookRepository).getReferenceById(1);
    }

    @Test
    public void saveBook() {
        final Book book = new Book();

        BDDMockito.given(bookRepository.save(book)).willReturn(book);

        final Book expected = bookService.saveBook(book);

        Assertions.assertEquals(expected, book);
        Mockito.verify(bookRepository).save(book);
    }

    @Test
    public void updateBook() {
        final Book book = new Book();
        book.setIsbn(1);

        BDDMockito.given(bookRepository.save(book)).willReturn(book);

        final Book expected = bookService.updateBook(book);

        Assertions.assertEquals(expected.getIsbn(), book.getIsbn());
        Mockito.verify(bookRepository).save(book);
    }

    @Test
    public void checkOut() {
        final List<Book> books = new ArrayList<Book>();

        final Book book1 = new Book(1, "jjf", "jfjf", "jfjf", "fiction", 93.0);
        final Book book2 = new Book(1, "jjf", "jfjf", "jfjf", "jjf", 94.0);

        books.add(book1);
        books.add(book2);

        final double ans = 93 * 0.9d + 94;

        Assertions.assertEquals(ans, bookService.checkout(books));
    }

}
