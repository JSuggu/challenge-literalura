package com.challenge_literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDtoResponse {
    private String title;
    private List<AuthorDto> authors;
    private List<String> languages;
    @JsonProperty("download_count")
    private int downloadCount;

    public String getAuthorsAsString() {
        if (authors == null || authors.isEmpty()) {
            return "Autor desconocido";
        }
        return authors.stream()
                .map(AuthorDto::getName)
                .collect(Collectors.joining(", "));
    }

    public String getFirstLanguage() {
        return (languages == null || languages.isEmpty())
                ? "Idioma desconocido"
                : languages.get(0);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }
}
