package com.abid.service;

import java.util.List;

import com.abid.model.Book;

public interface BookService {
	
	
    public List<Book> getAllBookDetails();

    public Book getBookDetail(Integer bookId);

    public Book saveBook(Book Book);

    public Book updateBook(Book book);

    public void deleteBook(Integer bookId);

    public Double checkout(List<Book> bookList);
	
	

}
