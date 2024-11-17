package com.github.vvsslova.dao;

import com.github.vvsslova.dao.mapper.PersonMapper;
import com.github.vvsslova.models.Book;
import com.github.vvsslova.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PeopleDAOImpl implements PeopleDAO {
    private final JdbcTemplate jdbcTemplate;
    private final BookDAOImpl bookDAO;

    @Autowired
    public PeopleDAOImpl(JdbcTemplate jdbcTemplate, BookDAOImpl bookDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Person> getAllPeople() {
        return jdbcTemplate.query("SELECT * FROM people", new PersonMapper());
    }

    @Override
    public Optional<Person> show(int id) {
        return jdbcTemplate.query("SELECT * FROM people WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny();
    }

    @Override
    public void addNewPerson(Person person) {
        jdbcTemplate.update("INSERT INTO people (name, year_of_birth) VALUES (?,?)", person.getName(), person.getYearOfBirth());
    }

    @Override
    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE people SET name=?, year_of_birth=? WHERE id=?", person.getName(),
                person.getYearOfBirth(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM people WHERE id=?", id);
    }

    @Override
    public List<Book> getPersonBooks(int id) {
        return bookDAO.checkBookLending(id);
    }
}
