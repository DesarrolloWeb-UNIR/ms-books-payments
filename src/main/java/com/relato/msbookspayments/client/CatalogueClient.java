package com.relato.msbookspayments.client;

import com.relato.msbookspayments.dto.BookDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CatalogueClient {

    private final RestTemplate restTemplate;
    
    
    public CatalogueClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BookDTO getBookById(Long id) {

        String url = "https://ms-api-gateway-production-7db9.up.railway.app/catalogue" + "/api/books/" + id;

        return restTemplate.getForObject(url, BookDTO.class);
    }
}
