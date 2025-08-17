package com.challenge_literalura.service;

import com.challenge_literalura.dto.ApiDtoResponse;
import com.challenge_literalura.dto.AuthorDto;
import com.challenge_literalura.dto.BookDtoResponse;
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
    public List<BookDtoResponse> getBooksByName(String bookName) throws IOException, InterruptedException {
        bookName = bookName.replaceAll(" ", "%20");
        HttpRequest req = HttpRequest.newBuilder()
                .header("content-type", "application/json")
                .uri(URI.create(apiUrl+"/books/?search="+bookName.toLowerCase()))
                .GET()
                .build();

        HttpResponse<String> response = http.send(req, BodyHandlers.ofString());

        ApiDtoResponse mappedResponse = mapper.readValue(response.body(), ApiDtoResponse.class);

        if (mappedResponse.getResults() != null) {
            for (BookDtoResponse book : mappedResponse.getResults()) {
                if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
                    for (AuthorDto authorDto : book.getAuthors()) {
                        if (!authorService.existAuthor(authorDto.getName())) {
                            authorService.saveAuthor(authorDto);
                        }
                    }
                }
            }
        }


        return mappedResponse.getResults();
    }

    private void saveAuthor(AuthorDto author){
        authorService.saveAuthor(author);
    }
}
