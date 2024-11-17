package com.github.vvsslova.dao;

import com.github.vvsslova.models.Book;
import com.github.vvsslova.models.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    @Transactional
    List<Book> getAllBooks();

    @Transactional
    Optional<Book> show(int id);

    @Transactional
    void addNewBook(Book book);

    @Transactional
    void update(int id, Book book);

    @Transactional
    void delete(int id);

    @Transactional
    List<Book> checkBookLending(int id);

    @Transactional
    List<Person> getLentPerson(int id);

    @Transactional
    void returnBook(int id);

    @Transactional
    void lendBook(int bookId, int personId);
}
