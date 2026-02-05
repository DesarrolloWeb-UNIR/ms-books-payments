package com.relato.msbookspayments.mapper;

import com.relato.msbookspayments.dto.PurchaseResponseDTO;
import com.relato.msbookspayments.entity.Purchase;

public class PurchaseMapper {

    private PurchaseMapper() {
        // Evita instanciación
    }

    public static PurchaseResponseDTO toDTO(Purchase purchase) {
        return new PurchaseResponseDTO(
                purchase.getId(),
                purchase.getBookId(),
                purchase.getStatus(),
                purchase.getPurchaseDate()
        );
    }
}