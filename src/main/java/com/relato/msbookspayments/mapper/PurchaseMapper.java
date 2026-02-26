package com.relato.msbookspayments.mapper;

import com.relato.msbookspayments.dto.BookDTO;
import com.relato.msbookspayments.dto.PurchaseResponseDTO;
import com.relato.msbookspayments.entity.Purchase;

public class PurchaseMapper {

    private PurchaseMapper() {
        // Evita instanciación
    }

    // Método original para cuando no hay información del libro
    public static PurchaseResponseDTO toDTO(Purchase purchase) {
        return new PurchaseResponseDTO(
                purchase.getId(),
                purchase.getBookId(),
                purchase.getStatus(),
                purchase.getPurchaseDate(),
                null
        );
    }

    // Nuevo método sobrecargado que incluye la información del libro
    public static PurchaseResponseDTO toDTO(Purchase purchase, BookDTO book) {
        return new PurchaseResponseDTO(
                purchase.getId(),
                purchase.getBookId(),
                purchase.getStatus(),
                purchase.getPurchaseDate(),
                book
        );
    }
}