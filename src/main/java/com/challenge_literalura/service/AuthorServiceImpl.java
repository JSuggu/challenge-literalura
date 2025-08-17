package com.challenge_literalura.service;

import com.challenge_literalura.dto.AuthorDto;
import com.challenge_literalura.entity.Author;
import com.challenge_literalura.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(AuthorDto authorDto){
        Author newAuthor = new Author();

        newAuthor.setName(authorDto.getName());
        newAuthor.setBirthYear(authorDto.getBirthYear());
        newAuthor.setDeathYear(authorDto.getDeathYear());

        return authorRepository.save(newAuthor);
    }

    public boolean existAuthor(String name){
        return authorRepository.existsByName(name);
    }

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(author -> {
                        AuthorDto authorDto = new AuthorDto();
                        authorDto.setName(author.getName());
                        authorDto.setBirthYear(author.getBirthYear());
                        authorDto.setDeathYear(author.getDeathYear());
                        return authorDto;
                    }
                )
                .collect(Collectors.toList());
    }
}
