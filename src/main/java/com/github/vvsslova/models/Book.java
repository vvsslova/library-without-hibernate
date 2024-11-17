package com.github.vvsslova.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    private int id;
    @NotEmpty(message = "Title should not be empty!")
    @Size(min = 5, max = 70, message = "Title should be between 2 and 40 characters!")
    private String title;
    @NotEmpty(message = "Author should not be empty!")
    @Size(min = 5, max = 70, message = "Author should be between 2 and 40 characters!")
    private String author;
    @Max(value = 2024, message = "Year of publication should not be grater than 2024!")
    private int yearOfPublication;
}
