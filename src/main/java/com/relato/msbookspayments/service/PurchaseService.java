package com.relato.msbookspayments.service;

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

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public PurchaseResponseDTO createPurchase(CreatePurchaseRequest request) {
        Purchase purchase = new Purchase(
                request.getBookId(),
                request.getUserEmail()
        );

        Purchase saved = purchaseRepository.save(purchase);
        return PurchaseMapper.toDTO(saved);
    }

    public List<PurchaseResponseDTO> getAllPurchases() {
        return purchaseRepository.findAll()
                .stream()
                .map(PurchaseMapper::toDTO)
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
        return PurchaseMapper.toDTO(purchaseRepository.save(purchase));
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
        return PurchaseMapper.toDTO(purchaseRepository.save(purchase));
    }

    private Purchase findPurchase(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Purchase not found"));
    }
}