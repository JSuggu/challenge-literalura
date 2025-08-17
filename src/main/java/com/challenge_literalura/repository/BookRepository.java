package com.challenge_literalura.repository;

import com.challenge_literalura.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);
    @Query(nativeQuery = true,
            value = "SELECT * FROM books WHERE LOWER(?1) = ANY(SELECT LOWER(unnest(languages)))")
    List<Book> findByLanguageIgnoreCase(String language);
}
