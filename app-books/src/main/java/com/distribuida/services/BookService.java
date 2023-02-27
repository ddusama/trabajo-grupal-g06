package com.distribuida.services;

import com.distribuida.db.Book;
import java.util.List;

public interface BookService {

    List<Book> getBooks();
    void createBook(Book book);
    Book getBookById(Integer id);
    void updateBook(Integer id,Book book);

    void delete(Integer id);

}
