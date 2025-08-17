package com.challenge_literalura.service;

import com.challenge_literalura.dto.AuthorDto;
import com.challenge_literalura.entity.Author;

import java.util.List;

public interface AuthorService {
    Author saveAuthor(AuthorDto authorDto);
    boolean existAuthor(String name);
    List<AuthorDto> getAll();
    List<AuthorDto> getAliveAuthorsByYear(Integer year);
}
