package com.springbootrest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootrest.entities.Book;
import com.springbootrest.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookservice;
	
	//get all books
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks()
	{
		try {
			
			List<Book> list=this.bookservice.getBookList();
			if(list.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.of(Optional.of(list));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}	
		 
	}
	
	//Get single book by its Id
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookbyId(@PathVariable("id") int id)
	{
		try {
			Book book=this.bookservice.getBookById(id);		
			return ResponseEntity.ok(book);
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
			// TODO: handle exception
		}
		
		
		
	}
	
	//add book 
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book)
	{
		try {
			Book bookadded=this.bookservice.addBook(book);
			return ResponseEntity.ok(bookadded);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
			}
		
	}
	
	//delete book by its id
	@DeleteMapping("/books/{bookid}")
	public ResponseEntity<Void> deleteBookbyId(@PathVariable("bookid") int bookid) {
		
		try {
			this.bookservice.deleteBook(bookid);
			return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		
	}
	
	//update book by id
	@PutMapping("books/{bookid}")
	public ResponseEntity<List<Book>> updateBookId(@RequestBody Book book,@PathVariable("bookid") int bookid )
	{
		try {
			
			List<Book> booklist=	this.bookservice.updateBook(book, bookid);
			return ResponseEntity.ok().body(booklist);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
			
		}
		
	
		
	}
	
	
	
	
	
}
