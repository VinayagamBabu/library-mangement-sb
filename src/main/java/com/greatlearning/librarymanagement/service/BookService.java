package com.greatlearning.librarymanagement.service;

import java.util.List;

import com.greatlearning.librarymanagement.model.Book;

public interface BookService {
	
	public List<Book> findAllBooks();
	public Book findById(int id); //Integer
	public Book save(Book book);
	public void deleteById(int id);
	public List<Book> search(String name, String author);

}
