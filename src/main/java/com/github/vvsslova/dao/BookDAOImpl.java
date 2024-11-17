package com.github.vvsslova.dao;

import com.github.vvsslova.dao.mapper.BookMapper;
import com.github.vvsslova.dao.mapper.PersonMapper;
import com.github.vvsslova.models.Book;
import com.github.vvsslova.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BookDAOImpl implements BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM books", new BookMapper());
    }

    @Override
    public Optional<Book> show(int id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE id=?", new Object[]{id}, new BookMapper())
                .stream().findAny();
    }

    @Override
    public void addNewBook(Book book) {
        jdbcTemplate.update("INSERT INTO books (title, author, year_of_production) VALUES (?,?,?)", book.getTitle(), book.getAuthor(), book.getYearOfPublication());
    }

    @Override
    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE books SET title=?, author=?, year_of_production=? WHERE id=?", book.getTitle(),
                book.getAuthor(), book.getYearOfPublication(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM books WHERE id=?", id);
    }

    @Override
    public List<Book> checkBookLending(int id) {
        List<Book> books = jdbcTemplate.query("SELECT books.id, books.title, books.author, books.year_of_production FROM people LEFT JOIN books ON people.id= books.person_id WHERE people.id=?",
                new Object[]{id}, new BookMapper()).stream().filter(Objects::nonNull).toList();
        return books;
    }

    @Override
    public List<Person> getLentPerson(int id) {
        List<Person> person = jdbcTemplate.query("SELECT * FROM people RIGHT JOIN books ON people.id= books.person_id WHERE books.id=?",
                new Object[]{id}, new PersonMapper()).stream().filter(Objects::nonNull).findFirst().stream().toList();
        return person;
    }

    @Override
    public void returnBook(int id) {
        jdbcTemplate.update("UPDATE books SET person_id = NULL WHERE id=?", id);
    }

    @Override
    public void lendBook(int bookId, int personId) {
        jdbcTemplate.update("UPDATE books SET person_id = ? WHERE books.id=?", personId, bookId);
    }
}
