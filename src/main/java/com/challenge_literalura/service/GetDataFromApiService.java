package com.challenge_literalura.service;

import com.challenge_literalura.dto.BookDtoResponse;

import java.io.IOException;
import java.util.List;

public interface GetDataFromApiService {
    List<BookDtoResponse> getBooksByName(String bookName) throws IOException, InterruptedException;
}
