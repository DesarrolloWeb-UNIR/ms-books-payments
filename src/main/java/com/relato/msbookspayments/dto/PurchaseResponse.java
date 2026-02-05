package com.relato.msbookspayments.dto;

import com.relato.msbookspayments.enums.PurchaseStatus;

import java.time.LocalDateTime;

public class PurchaseResponse {

    private Long id;
    private Long bookId;
    private String userEmail;
    private PurchaseStatus status;
    private LocalDateTime purchaseDate;

    public PurchaseResponse(
            Long id,
            Long bookId,
            String userEmail,
            PurchaseStatus status,
            LocalDateTime purchaseDate
    ) {
        this.id = id;
        this.bookId = bookId;
        this.userEmail = userEmail;
        this.status = status;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
}