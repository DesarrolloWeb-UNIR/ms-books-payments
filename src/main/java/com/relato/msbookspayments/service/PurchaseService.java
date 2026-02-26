package com.relato.msbookspayments.service;

import com.relato.msbookspayments.client.CatalogueClient;
import com.relato.msbookspayments.dto.BookDTO;
import com.relato.msbookspayments.dto.CreatePurchaseRequest;
import com.relato.msbookspayments.dto.PurchaseResponseDTO;
import com.relato.msbookspayments.entity.Purchase;
import com.relato.msbookspayments.enums.PurchaseStatus;
import com.relato.msbookspayments.exception.BusinessException;
import com.relato.msbookspayments.mapper.PurchaseMapper;
import com.relato.msbookspayments.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CatalogueClient catalogueClient;

    public PurchaseService(
            PurchaseRepository purchaseRepository,
            CatalogueClient catalogueClient
    ) {
        this.purchaseRepository = purchaseRepository;
        this.catalogueClient = catalogueClient;
    }

    public PurchaseResponseDTO createPurchase(CreatePurchaseRequest request) {

        // 1. Consultar libro antes de comprar
        BookDTO book;

        try {
            book = catalogueClient.getBookById(request.getBookId());
        } catch (Exception e) {
            throw new BusinessException("Book not available");
        }

        // 2. Crear compra
        Purchase purchase = new Purchase(
                request.getBookId(),
                request.getUserEmail()
        );

        Purchase saved = purchaseRepository.save(purchase);

        // 3. Responder compra + info del libro
        return new PurchaseResponseDTO(
                saved.getId(),
                saved.getBookId(),
                saved.getStatus(),
                saved.getPurchaseDate(),
                book
        );
    }

    public List<PurchaseResponseDTO> getAllPurchases() {
        return purchaseRepository.findAll()
                .stream()
                .map(purchase -> {
                    try {
                        // Obtener información del libro para cada compra
                        BookDTO book = catalogueClient.getBookById(purchase.getBookId());
                        return new PurchaseResponseDTO(
                                purchase.getId(),
                                purchase.getBookId(),
                                purchase.getStatus(),
                                purchase.getPurchaseDate(),
                                book
                        );
                    } catch (Exception e) {
                        // Si no se puede obtener el libro, devolver solo la compra
                        return PurchaseMapper.toDTO(purchase);
                    }
                })
                .toList();
    }

    public PurchaseResponseDTO confirmPurchase(Long id) {
        Purchase purchase = findPurchase(id);

        if (purchase.getStatus() == PurchaseStatus.CONFIRMED) {
            throw new BusinessException("Purchase already confirmed");
        }

        if (purchase.getStatus() == PurchaseStatus.REJECTED) {
            throw new BusinessException("Cannot confirm a rejected purchase");
        }

        purchase.setStatus(PurchaseStatus.CONFIRMED);
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // Obtener información del libro para la respuesta
        try {
            BookDTO book = catalogueClient.getBookById(savedPurchase.getBookId());
            return new PurchaseResponseDTO(
                    savedPurchase.getId(),
                    savedPurchase.getBookId(),
                    savedPurchase.getStatus(),
                    savedPurchase.getPurchaseDate(),
                    book
            );
        } catch (Exception e) {
            // Si falla la obtención del libro, devolver solo la compra
            return PurchaseMapper.toDTO(savedPurchase);
        }
    }

    public PurchaseResponseDTO rejectPurchase(Long id) {
        Purchase purchase = findPurchase(id);

        if (purchase.getStatus() == PurchaseStatus.REJECTED) {
            throw new BusinessException("Purchase already rejected");
        }

        if (purchase.getStatus() == PurchaseStatus.CONFIRMED) {
            throw new BusinessException("Cannot reject a confirmed purchase");
        }

        purchase.setStatus(PurchaseStatus.REJECTED);
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // Obtener información del libro para la respuesta
        try {
            BookDTO book = catalogueClient.getBookById(savedPurchase.getBookId());
            return new PurchaseResponseDTO(
                    savedPurchase.getId(),
                    savedPurchase.getBookId(),
                    savedPurchase.getStatus(),
                    savedPurchase.getPurchaseDate(),
                    book
            );
        } catch (Exception e) {
            // Si falla la obtención del libro, devolver solo la compra
            return PurchaseMapper.toDTO(savedPurchase);
        }
    }

    public Purchase findPurchase(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Purchase not found"));
    }
}