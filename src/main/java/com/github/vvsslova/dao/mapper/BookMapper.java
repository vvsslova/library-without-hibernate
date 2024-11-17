package com.github.vvsslova.dao.mapper;

import com.github.vvsslova.models.Book;
import com.github.vvsslova.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        if (rs.getInt("id") == 0 & rs.getString("title") == null &
                rs.getString("author") == null & rs.getInt("year_of_production") == 0) {
            return null;
        }
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setYearOfPublication(rs.getInt("year_of_production"));
        return book;
    }
}
