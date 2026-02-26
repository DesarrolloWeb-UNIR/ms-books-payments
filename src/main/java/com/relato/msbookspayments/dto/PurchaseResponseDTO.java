package com.relato.msbookspayments.dto;

import com.relato.msbookspayments.enums.PurchaseStatus;

import java.time.LocalDateTime;

public class PurchaseResponseDTO {

    private Long id;
    private Long bookId;
    private PurchaseStatus status;
    private LocalDateTime purchaseDate;
    private BookDTO book;

    public PurchaseResponseDTO(
            Long id,
            Long bookId,
            PurchaseStatus status,
            LocalDateTime purchaseDate,
            BookDTO book
    ) {
        this.id = id;
        this.bookId = bookId;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
    public BookDTO getBook() {
        return book;
    }
}