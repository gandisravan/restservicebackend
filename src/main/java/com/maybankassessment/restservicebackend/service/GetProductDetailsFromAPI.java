package com.maybankassessment.restservicebackend.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.maybankassessment.restservicebackend.model.Product;

@Service
public class GetProductDetailsFromAPI {

	private final RestTemplate restTemplate;
	
    public GetProductDetailsFromAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getDetailsFromOtherApi(Long id) {
        String url = "http://localhost:8090/getapi/product/getproductByID/" + id;

        ResponseEntity<Product> response =
                restTemplate.getForEntity(url, Product.class);

        return response.getBody();
    }
}

