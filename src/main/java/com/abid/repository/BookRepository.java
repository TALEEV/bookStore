package com.abid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abid.model.Book;


public interface BookRepository extends JpaRepository<Book, Integer> {

}
