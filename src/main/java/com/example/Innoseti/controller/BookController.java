package com.example.Innoseti.controller;

import com.example.Innoseti.model.Author;
import com.example.Innoseti.model.Book;
import com.example.Innoseti.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Ilya Grishin
 */
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService service;


    @QueryMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @QueryMapping
    public List<Book> getBooksByAuthor(@Argument Author author) {
        return service.getBooksByAuthor(author);
    }

    @MutationMapping
    public Book saveBook(@Argument String title, @Argument List<Author> authors) {
        return service.saveBook(title, authors);
    }
}
