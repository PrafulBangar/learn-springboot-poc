package com.springbootrest.dao;

import org.springframework.data.repository.CrudRepository;

import com.springbootrest.entities.Book;

public interface BookRepository extends CrudRepository<Book,Integer>{
public Book findById(int id);
}
