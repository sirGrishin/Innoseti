package com.example.Innoseti.service;

import com.example.Innoseti.model.Author;
import com.example.Innoseti.model.Book;
import com.example.Innoseti.repo.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ilya Grishin
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author getByName(String name) {
        return authorRepository.findByName(name);
    }


    public Author findByIdAndName(Integer id, String name) {
        try {
            return authorRepository.findByIdAndName(id, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public Author saveAuthor(String name, List<Book> books) {
        //тут можно добавить проверку на существующего автора,
        // но полей недостаточно, имя может быть не уникально
        Author author = new Author();
        author.setName(name);
        author.setBooks(books);
        authorRepository.save(author);
        return author;
    }
}
