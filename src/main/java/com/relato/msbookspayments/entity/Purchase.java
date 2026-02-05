package com.relato.msbookspayments.entity;

import com.relato.msbookspayments.enums.PurchaseStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private LocalDateTime purchaseDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PurchaseStatus status;
    // Constructor vacío (OBLIGATORIO para JPA)
    public Purchase() {
    }

    // Constructor de conveniencia
    public Purchase(Long bookId, String userEmail) {
        this.bookId = bookId;
        this.userEmail = userEmail;
        this.purchaseDate = LocalDateTime.now();
        this.status = PurchaseStatus.CREATED;
    }

    // Getters (por ahora no necesitamos setters)
    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }

}
