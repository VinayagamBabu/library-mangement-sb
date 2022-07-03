package com.greatlearning.librarymanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name ="book")
@Table
@Getter
@Setter
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "category")
	private String category;
	@Column(name = "author")
	private String author;
	
	public Book() {}

	public Book(String name, String category, String author) {
		super();
		this.name = name;
		this.category = category;
		this.author = author;
	}
	
	
}
