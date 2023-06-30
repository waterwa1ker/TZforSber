package com.TaskForSber.controllers;

import com.TaskForSber.dto.BookDTO;
import com.TaskForSber.dto.CommentDTO;
import com.TaskForSber.dto.UserDTO;
import com.TaskForSber.exceptions.books.BookNotFoundException;
import com.TaskForSber.exceptions.comments.CommentNotCreatedException;
import com.TaskForSber.models.Book;
import com.TaskForSber.models.Comment;
import com.TaskForSber.models.User;
import com.TaskForSber.services.BookService;
import com.TaskForSber.services.CommentService;
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
    private final CommentService commentService;

    @Autowired
    public BookController(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @GetMapping
    public List<BookDTO> findAll(){
        return bookService.findAll().stream().map(this::convertToBookDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable int id){
        BookDTO bookDTO = null;
        try{
            bookDTO = convertToBookDTO(bookService.findById(id));
        }
        catch (BookNotFoundException e){
            e.getMessage();
        }
        return bookDTO;
    }

    @GetMapping("/{id}/comments")
    public List<CommentDTO> findAllComments(@PathVariable int id){
        Book book = bookService.findById(id);
        return book.getComments().stream().map(this::convertToCommentDTO).collect(Collectors.toList());
    }

    @PostMapping("/{id}/comments/create")
    public ResponseEntity<HttpStatus> createComment(@RequestBody @Valid CommentDTO commentDTO,
                                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new CommentNotCreatedException("Comment not created");
        }
        commentService.save(convertToComment(commentDTO));
        return ResponseEntity.ok(HttpStatus.OK);

    }

    private CommentDTO convertToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO(comment.getText(),
                comment.getRating(),
                convertToUserDTO(comment.getUser()),
                convertToBookDTO(comment.getBook()));
        return commentDTO;

    }

    private BookDTO convertToBookDTO(Book book){
        BookDTO bookDTO = new BookDTO(book.getTitle(),
                book.getAuthor(),
        book.getYearOfPublication(),
                convertToUserDTO(book.getUser()));
        return bookDTO;
    }

    private UserDTO convertToUserDTO(User user){
        UserDTO userDTO = new UserDTO(user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getYearOfBirth());
        return userDTO;
    }

    private Comment convertToComment(CommentDTO commentDTO){
        Comment comment = new Comment(commentDTO.getText(),
                commentDTO.getRating(),
                convertToUser(commentDTO.getUserDTO()),
                convertToBook(commentDTO.getBookDTO()));
        return comment;
    }

    private User convertToUser(UserDTO userDTO){
        User user = new User(userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getName(),
                userDTO.getYearOfBirth());
        return user;
    }

    private Book convertToBook(BookDTO bookDTO){
        Book book = new Book(bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getYearOfPublication(),
                convertToUser(bookDTO.getUserDTO()));
        return book;
    }
}
