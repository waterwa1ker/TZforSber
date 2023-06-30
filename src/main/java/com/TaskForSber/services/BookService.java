package com.TaskForSber.services;

import com.TaskForSber.exceptions.books.BookNotFoundException;
import com.TaskForSber.models.Book;
import com.TaskForSber.repositories.BookRepository;
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
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null)
            throw new BookNotFoundException("Book not found");
        return book;
    }
}
