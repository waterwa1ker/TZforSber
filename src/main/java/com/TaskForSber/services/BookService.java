package com.TaskForSber.services;

import com.TaskForSber.models.Book;
import com.TaskForSber.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(int id){
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Book book){
        bookRepository.delete(book);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }
}
