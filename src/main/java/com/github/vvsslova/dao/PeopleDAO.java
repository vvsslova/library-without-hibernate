package com.github.vvsslova.dao;

import com.github.vvsslova.models.Book;
import com.github.vvsslova.models.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PeopleDAO {
    @Transactional
    List<Person> getAllPeople();

    @Transactional
    Optional<Person> show(int id);

    @Transactional
    void addNewPerson(Person person);

    @Transactional
    void update(int id, Person person);

    @Transactional
    void delete(int id);

    @Transactional
    List<Book> getPersonBooks(int id);
}
