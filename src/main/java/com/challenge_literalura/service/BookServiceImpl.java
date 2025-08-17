package com.challenge_literalura.service;

import com.challenge_literalura.dto.AuthorDto;
import com.challenge_literalura.dto.BookDto;
import com.challenge_literalura.entity.Author;
import com.challenge_literalura.entity.Book;
import com.challenge_literalura.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book saveBook(BookDto bookDto, Author author){
        Book newBook = new Book();
        newBook.setTitle(bookDto.getTitle());
        newBook.setLanguages(bookDto.getLanguages());
        newBook.setDownloadCount(bookDto.getDownloadCount());
        newBook.setAuthor(author);

        return bookRepository.save(newBook);
    }

    @Override
    public boolean existBook(String title) {
        return bookRepository.existsByTitle(title);
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByLanguage(String language) {
        return bookRepository.findByLanguageIgnoreCase(language).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BookDto convertToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setLanguages(book.getLanguages());
        bookDto.setDownloadCount(book.getDownloadCount());

        if (book.getAuthor() != null) {
            AuthorDto authorDto = new AuthorDto();
            bookDto.setAuthors(List.of(authorDto));
        }

        return bookDto;
    }

}
