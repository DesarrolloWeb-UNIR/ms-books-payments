package com.relato.msbookspayments.controller;

import com.relato.msbookspayments.dto.CreatePurchaseRequest;
import com.relato.msbookspayments.dto.PurchaseResponseDTO;
import com.relato.msbookspayments.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseResponseDTO createPurchase(
            @Valid @RequestBody CreatePurchaseRequest request
    ) {
        return purchaseService.createPurchase(request);
    }

    @GetMapping
    public List<PurchaseResponseDTO> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @PutMapping("/{id}/confirm")
    public PurchaseResponseDTO confirmPurchase(@PathVariable Long id) {
        return purchaseService.confirmPurchase(id);
    }

    @PutMapping("/{id}/reject")
    public PurchaseResponseDTO rejectPurchase(@PathVariable Long id) {
        return purchaseService.rejectPurchase(id);
    }

    @GetMapping("/ping")
    public String ping() {
        return "Payments OK desde Eureka + Gateway";
    }
}