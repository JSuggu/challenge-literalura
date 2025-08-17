package com.challenge_literalura.service;

import com.challenge_literalura.dto.BookDto;

import java.io.IOException;
import java.util.List;

public interface GetDataFromApiService {
    List<BookDto> getBooksByName(String bookName) throws IOException, InterruptedException;
}
