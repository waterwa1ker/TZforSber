package com.TaskForSber.controller;

import com.TaskForSber.dto.BookDTO;
import com.TaskForSber.models.Book;
import com.TaskForSber.services.BookService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> findAll(){
        return bookService.findAll().stream().map(this::convertToBookDTO).collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> setBusy(@PathVariable int id,
                                              @RequestParam(required = false, value = "value") String value){
        Book book = bookService.findById(id);
        if (book != null){
            if (value.equals("free"))
                book.setFree(true);
            else if (value.equals("busy"))
                book.setFree(false);
            else
                return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
            bookService.save(book);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.ok(HttpStatus.NOT_FOUND);
    }

    private BookDTO convertToBookDTO(Book book){
        BookDTO bookDTO = new BookDTO(book.getTitle(),book.getAuthor(), book.getYear());
        bookDTO.setFree(book.isFree());
        return bookDTO;
    }




}
