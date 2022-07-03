package com.greatlearning.librarymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.librarymanagement.model.Book;
import com.greatlearning.librarymanagement.repository.BooksRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BooksRepository booksRepository;

	@Override
	public List<Book> findAllBooks() {
		return booksRepository.findAll();
	}

	@Override
	public Book findById(int id) {
		return booksRepository.findById(id).get();
	}

	@Override
	public Book save(Book book) {
		return booksRepository.save(book);
	}

	@Override
	public void deleteById(int id) {
		booksRepository.deleteById(id);
		
	}

	@Override
	public List<Book> search(String name, String author) {
		return booksRepository.findByNameContainsAndAuthorContainsAllIgnoreCase(name, author);
	}

}
