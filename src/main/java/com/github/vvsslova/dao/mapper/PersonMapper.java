package com.github.vvsslova.dao.mapper;

import com.github.vvsslova.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper <Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        if (rs.getInt("id") == 0 & rs.getString("name") == null
                & rs.getInt("year_of_birth") == 0) {
            return null;
        }
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setYearOfBirth(rs.getInt("year_of_birth"));
        return person;
    }
}
