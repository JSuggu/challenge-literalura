package com.challenge_literalura.service;

import java.io.IOException;

public interface IGetDataFromApiService {
    String getBooksByName(String bookName) throws IOException, InterruptedException;
}
