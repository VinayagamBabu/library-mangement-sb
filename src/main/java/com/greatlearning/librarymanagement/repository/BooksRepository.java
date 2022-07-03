package com.greatlearning.librarymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.librarymanagement.model.Book;

@Repository
public interface BooksRepository  extends JpaRepository<Book, Integer>{
	
	List<Book> findByNameContainsAndAuthorContainsAllIgnoreCase(String name, String author);
	 
}
