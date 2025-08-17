package com.challenge_literalura.service;

import com.challenge_literalura.dto.ApiDto;
import com.challenge_literalura.dto.AuthorDto;
import com.challenge_literalura.dto.BookDto;
import com.challenge_literalura.entity.Author;
import com.challenge_literalura.entity.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

@Service
public class GetDataFromApiServiceImpl implements GetDataFromApiService {
    private final String apiUrl = "http://gutendex.com";
    private final HttpClient http = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final AuthorService authorService;
    private final BookService bookService;

    public GetDataFromApiServiceImpl(AuthorService authorService, BookService bookService){
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public List<BookDto> getBooksByName(String bookName) throws IOException, InterruptedException {
        bookName = bookName.replaceAll(" ", "%20");
        HttpRequest req = HttpRequest.newBuilder()
                .header("content-type", "application/json")
                .uri(URI.create(apiUrl+"/books/?search="+bookName.toLowerCase()))
                .GET()
                .build();

        HttpResponse<String> response = http.send(req, BodyHandlers.ofString());

        ApiDto mappedResponse = mapper.readValue(response.body(), ApiDto.class);

        if (mappedResponse.getResults() != null) {
            for (BookDto bookDto : mappedResponse.getResults()) {
                if (bookDto.getAuthors() != null && !bookDto.getAuthors().isEmpty()) {
                    for (AuthorDto authorDto : bookDto.getAuthors()) {
                        Author author = authorService.saveAuthor(authorDto);

                        if (!bookService.existBook(bookDto.getTitle())) {
                            bookService.saveBook(bookDto, author);
                        }
                    }
                }
            }
        }


        return mappedResponse.getResults();
    }
}
