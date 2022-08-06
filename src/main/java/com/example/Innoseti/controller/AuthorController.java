package com.example.Innoseti.controller;

import com.example.Innoseti.model.Author;
import com.example.Innoseti.model.Book;
import com.example.Innoseti.service.AuthorService;
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
public class AuthorController {

    private final AuthorService service;

    @QueryMapping
    public Author getAuthor(@Argument String name) {
        return service.getByName(name);
    }

    @MutationMapping
    public Author saveAuthor(@Argument String name, @Argument List<Book> books) {
        return service.saveAuthor(name, books);
    }


}
