package com.TaskForSber.controller;

import com.TaskForSber.dto.BookDTO;
import com.TaskForSber.dto.UserDTO;
import com.TaskForSber.exceptions.book.BookNotCreatedException;
import com.TaskForSber.models.Book;
import com.TaskForSber.models.User;
import com.TaskForSber.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid BookDTO bookDTO,
                                          BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new BookNotCreatedException("Book not added. Check your fields!");
        }
        bookService.save(convertToBook(bookDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){
        Book book = bookService.findById(id);
        if (book == null)
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        bookService.delete(book);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private BookDTO convertToBookDTO(Book book){
        BookDTO bookDTO = new BookDTO(book.getTitle(),book.getAuthor(), book.getYear());
        bookDTO.setFree(book.isFree());
        bookDTO.setPersonDTO(convertToUserDTO(book.getUser()));
        return bookDTO;
    }

    private UserDTO convertToUserDTO(User user){
        UserDTO userDTO = new UserDTO(user.getName(), user.getPassword(), user.getYear());
        return userDTO;
    }

    private Book convertToBook(BookDTO bookDTO){
        Book book = new Book(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getYear());
        return book;
    }




}
