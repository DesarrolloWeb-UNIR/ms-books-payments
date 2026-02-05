package com.relato.msbookspayments.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreatePurchaseRequest {

    @NotNull(message = "bookId is required")
    @Positive(message = "bookId must be greater than 0")
    private Long bookId;

    @NotBlank(message = "userEmail is required")
    @Email(message = "userEmail must be a valid email")
    private String userEmail;

    public CreatePurchaseRequest() {
    }

    public Long getBookId() {
        return bookId;
    }

    public String getUserEmail() {
        return userEmail;
    }
}