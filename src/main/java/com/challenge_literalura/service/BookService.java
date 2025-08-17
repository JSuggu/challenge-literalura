package com.challenge_literalura.service;

import com.challenge_literalura.dto.BookDto;
import com.challenge_literalura.entity.Author;
import com.challenge_literalura.entity.Book;

import java.util.List;

public interface BookService {
    Book saveBook(BookDto bookDto, Author author);
    boolean existBook(String title);
    List<BookDto> getAll();
}
