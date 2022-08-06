package com.example.Innoseti;

import com.example.Innoseti.model.Author;
import com.example.Innoseti.model.Book;
import com.example.Innoseti.service.AuthorService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Ilya Grishin
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest extends AbstractTest {

    @Autowired
    GraphQlTester graphQlTester;

    @Autowired
    AuthorService authorService;


    @Test
    @Order(1)
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:resource/sql/testData.sql")
    void getAuthor() {
        // language=GraphQL
        String document = """
                query {
                      getAuthor(name : "Алексей"){
                                id
                                name
                                books{
                                  id
                                  title
                                 }
                              }
                       }
                """;

        graphQlTester.document(document)
                .variable("name", "Алексей")
                .execute()
                .path("getAuthor")
                .entity(Author.class)
                .satisfies(author -> {
                    assertEquals("Алексей", author.getName());
                    assertTrue(author.getBooks().size() > 0);
                });
    }

    @Test
    @Order(2)
    void getAllBooks() {
        // language=GraphQL
        String document = """
                query {
                    getAllBooks {
                        id
                        title
                    }
                }            
                """;

        graphQlTester.document(document)
                .execute()
                .path("getAllBooks")
                .entityList(Book.class)
                .hasSize(4);

    }

    @Test
    @Order(3)
    void getBookByAuthor() {
        // language=GraphQL
        String document = """
                query{
                  getBooksByAuthor(author: {id:-1, name:"Алексей"}){
                    id
                    title
                  }
                }
                      """;

        graphQlTester.document(document)
                .execute()
                .path("getBooksByAuthor")
                .entityList(Book.class)
                .hasSize(2)
                .satisfies(books -> {
                    books.get(0).getTitle().equals("книга Алексея");
                    books.get(1).getTitle().equals("книга Алексея и Игоря");
                });

    }

    @Test
    @Order(4)
    void saveAuthor() {
        // language=GraphQL
        String document = """
                mutation{
                  saveAuthor(name: "Иван", books:[ {title: "книга Ивана"}, {title: "книга Ивана 2"}]){
                    id
                    name
                  }
                }
                     """;

        List<Book> books = new ArrayList<>();
        Book book = new Book();
//        book.setId(5);
        book.setTitle("книга Ивана");
        Book book1 = new Book();
//        book1.setId(6);
        book1.setTitle("книга Ивана 2");
        books.add(book);
        books.add(book1);

        graphQlTester.document(document)
                .variable("name", "Иван")
                .variable("books", books) //без этого тоже проходит
                .execute()
                .path("saveAuthor")
                .entity(Author.class)
                .satisfies(author -> {
                    assertNotNull(author.getId());
                    assertEquals("Иван", author.getName());
                });
        assertEquals(authorService.getByName("Иван").getBooks().size(), 2);
    }

    @Test
    @Order(5)
    void saveBook() {
        assertEquals(authorService.getByName("Игорь").getBooks().size(), 2);
        assertEquals(authorService.getByName("Александр").getBooks().size(), 1);
        // language=GraphQL
        String document = """
                mutation{
                    saveBook(title: "Книга Александра и Игоря", authors:[{id:-2, name: "Игорь"}, {id:-3, name: "Александр"}]){
                       id
                       title
                        }
                    }
                     """;

       List<Author> authorList = new ArrayList<>();
       authorList.add(authorService.getByName("Игорь"));
       authorList.add(authorService.getByName("Александр"));

        graphQlTester.document(document)
                .variable("title", "Книга Александра и Игоря")
                .variable("authors", authorList)
                .execute()
                .path("saveBook")
                .entity(Book.class)
                .satisfies(book -> {
                    assertNotNull(book.getId());
                    assertEquals("Книга Александра и Игоря", book.getTitle());
                });
        assertEquals(authorService.getByName("Игорь").getBooks().size(), 3);
        assertEquals(authorService.getByName("Александр").getBooks().size(), 2);
    }


}

