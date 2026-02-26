package com.relato.msbookspayments.dto;

public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private String category;
    private String isbn;
    private Integer rating;
    private Boolean visible;

    public BookDTO() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getRating() {
        return rating;
    }

    public Boolean getVisible() {
        return visible;
    }
}