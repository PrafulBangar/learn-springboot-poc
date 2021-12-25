package com.springbootrest.service;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbootrest.dao.BookRepository;
import com.springbootrest.entities.Book;

@Component
public class BookService {

	
	  private static List<Book> bookList = new ArrayList<>();
	  
	  static {
	  
	  bookList.add(new Book(1,"book1","author1"));
	  bookList.add(new Book(2,"book2","author2"));
	  bookList.add(new Book(3,"book3","author3"));
	  
	  }
	 
	
	
	@Autowired
	private BookRepository bookrepo;
	
	
	public List<Book> getBookList() {
	List<Book> list=(List<Book>)this.bookrepo.findAll();
	return list;
		}

	public Book getBookById(int id) {
		/*
		 * Book book = bookList.stream().filter(book -> book.getId() ==
		 * id).findFirst().get(); return book;
		 */	
		Book book=bookrepo.findById(id);
		return book;
		
	}

	public Book addBook(Book book) {
		/*
		 * bookList.add(book); return book;
		 */
		bookrepo.save(book);
		return book;
	}

	public void deleteBook(int id) {
		/*
		 * Book book = bookList.stream().filter(b -> b.getId() == id).findFirst().get();
		 * BookService.bookList.remove(book);
		 */
		bookrepo.deleteById(id)	;
		}

	public List<Book> updateBook(Book book, int bookid) {
		/*
		 * List<Book> bookslist=bookList.stream().map(b -> { if (b.getId() == bookid) {
		 * b.setTitle(book.getTitle()); b.setAuthor(book.getAuthor()); } return b;
		 * }).collect(Collectors.toList());
		 * 
		 * return bookslist;
		 */
		book.setId(bookid);
		bookrepo.save(book);
		
		List<Book> list=(List<Book>)bookrepo.findAll();
		return list;
	}
}
