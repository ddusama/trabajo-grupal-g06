package com.distribuida.services;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.neo4j.driver.Session;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    @Inject
    private Session session;

    @Override
    public List<Book> getBooks() {

        return session.run("Match (b:Book) RETURN ID(b) as id,b.title as title," +
                        "b.price as price,b.author as author,b.isbn as isbn")
                .stream()
                .map(record -> Book.builder()
                        .id(record.get("id").asInt())
                        .title(record.get("title").asString())
                        .price(record.get("price").asDouble())
                        .author(record.get("author").asString())
                        .isbn(record.get("isbn").asString())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public void createBook(Book book) {
        session.run("CREATE (b:Book{isbn:$isbn,title:$title,author:$author,price:$price})", getParams(book));
    }

    @Override
    public Book getBookById(Integer id) {
        return session
                .run("Match (b:Book)WHERE ID(b)=$id RETURN ID(b) as id,b.title as title,b.price as price,b.author as author,b.isbn as isbn", Map.of("id", id))
                .stream()
                .limit(1)
                .map(record -> Book.builder()
                        .id(record.get("id").asInt())
                        .title(record.get("title").asString())
                        .price(record.get("price").asDouble())
                        .author(record.get("author").asString())
                        .isbn(record.get("isbn").asString())
                        .build())
                .findFirst()
                .get();

    }

    @Override
    public void updateBook(Integer id, Book book) {
        var params=getParams(book);
        params.put("id",id);
        session.run("Match (b:Book) WHERE ID(b)=$id SET b.title=$title SET b.price=$price SET b.author=$author SET b.isbn=$isbn",params);

    }

    private Map<String, Object> getParams(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("isbn", book.getIsbn());
        params.put("title", book.getTitle());
        params.put("author", book.getAuthor());
        params.put("price", book.getPrice());
        return params;
    }

    @Override
    public void delete(Integer id) {
        session.run("Match (b:Book) WHERE ID(b)=$id DELETE b",Map.of("id",id));

    }
}
