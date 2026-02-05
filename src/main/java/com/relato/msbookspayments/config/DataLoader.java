package com.relato.msbookspayments.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.relato.msbookspayments.entity.Purchase;
import com.relato.msbookspayments.enums.PurchaseStatus;
import com.relato.msbookspayments.repository.PurchaseRepository;
@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner runner(PurchaseRepository purchaseRepository) {
        return args -> {
            Purchase purchase = new Purchase(
                    1L,
                    "usuario@relato.com"
            );

            purchaseRepository.save(purchase);

            System.out.println("✅ Compra guardada con ID: " + purchase.getId());
        };
    }
}
