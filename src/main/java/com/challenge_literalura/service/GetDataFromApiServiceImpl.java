package com.challenge_literalura.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

@Service
public class GetDataFromApiServiceImpl implements IGetDataFromApiService {
    private final String apiUrl = "http://gutendex.com";
    private final HttpClient http = HttpClient.newHttpClient();

    @Override
    public String getBooksByName(String bookName) throws IOException, InterruptedException {
        bookName = bookName.replaceAll(" ", "%20");
        HttpRequest req = HttpRequest.newBuilder()
                .header("content-type", "application/json")
                .uri(URI.create(apiUrl+"/books/?search="+bookName.toLowerCase()))
                .GET()
                .build();

        HttpResponse<String> response = http.send(req, BodyHandlers.ofString());

        return response.body();
    }
}
