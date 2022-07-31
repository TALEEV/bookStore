package com.abid.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abid.model.Book;
import com.abid.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_CLASSIFICATION_FICTION = "fiction";
    private BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
	}


    @Override
    public List<Book> getAllBookDetails() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookDetail(final Integer bookId) {

        return bookRepository.findById(bookId).get();

    }

    @Override
    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(final Book book) {
        final Book updateBook = Book.builder().isbn(book.getIsbn()).name(book.getName()).description(book.getDescription()).author(book.getAuthor())
                .classification(book.getClassification()).price(book.getPrice()).build();

        return bookRepository.save(updateBook);
    }

    @Override
    public void deleteBook(final Integer bookId) {
        bookRepository.deleteById(bookId);

    }

    @Override
    public Double checkout(final List<Book> books) {

        final double totalCost = books.stream().mapToDouble(book -> {
            if (book.getClassification().toLowerCase().equals(BOOK_CLASSIFICATION_FICTION)) {
                return book.getPrice() * 0.9d;
            } else {
                return book.getPrice();
            }
        }).sum();

        return totalCost;
    }

}
