package com.example.Innoseti.service;

import com.example.Innoseti.model.Author;
import com.example.Innoseti.model.Book;
import com.example.Innoseti.repo.BookRepository;
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
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    public List<Book> getBooksByAuthor(Author author) {
        try {
            return authorService.findByIdAndName(author.getId(), author.getName()).getBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(String title, List<Author> authors) {
        Book book = new Book();
        book.setTitle(title);
        bookRepository.save(book);
        authors.forEach(author -> authorService.findByIdAndName(author.getId(), author.getName()).getBooks().add(book));
        return book;
    }

}
