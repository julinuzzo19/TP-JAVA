package com.api.books.repository;

import com.api.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bookRepository")
public interface LibroRepository extends JpaRepository<Book, Integer> {

}
