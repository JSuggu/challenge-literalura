package com.challenge_literalura.repository;

import com.challenge_literalura.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Long, Author> {
}
