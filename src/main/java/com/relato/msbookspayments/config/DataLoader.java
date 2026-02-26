package com.relato.msbookspayments.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.relato.msbookspayments.client.CatalogueClient;
import com.relato.msbookspayments.dto.BookDTO;
import com.relato.msbookspayments.entity.Purchase;
import com.relato.msbookspayments.repository.PurchaseRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner runner(PurchaseRepository purchaseRepository, CatalogueClient catalogueClient) {
        return args -> {
            Purchase purchase = new Purchase(10L, "usuario@relato.com");
            Purchase saved = purchaseRepository.save(purchase);
            System.out.println("✅ Compra guardada con ID: " + saved.getId());

            try {
                BookDTO book = catalogueClient.getBookById(10L);
                System.out.println("✅ Libro encontrado: " + book.getTitle() + " - " + book.getAuthor());
            } catch (Exception e) {
                System.out.println("❌ Error al obtener libro: " + e.getMessage());
            }
        };
    }
}
