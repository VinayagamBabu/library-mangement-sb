package com.greatlearning.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.librarymanagement.model.Book;
import com.greatlearning.librarymanagement.service.BookService;

@Controller
@RequestMapping("/books")
public class BooksController {
	
	@Autowired
	BookService bookService;
	
	@RequestMapping("/list")
	public String getAllBooks(Model model) {
		List<Book> books = bookService.findAllBooks();
		model.addAttribute("Books",books);
		return "list-Books";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Book book = new Book();
		model.addAttribute("Book",book);
		return "Book-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bookId")int id,Model model) {
		Book book = bookService.findById(id);
		model.addAttribute("Book",book);
		return "Book-form";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("Book") Book book) {
		bookService.save(book);
		return "redirect:/books/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("bookId")int id) {
		bookService.deleteById(id);
		return "redirect:/books/list";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("name") String name, @RequestParam("author") String author,Model model) {
		if(!name.isBlank() || !author.isBlank()) {
			List<Book> books = bookService.search(name, author);
			model.addAttribute("Books", books);
			return "list-Books";
		}
		return "redirect:/books/list";
	}
}
