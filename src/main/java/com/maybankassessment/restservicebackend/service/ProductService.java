package com.maybankassessment.restservicebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maybankassessment.restservicebackend.model.Product;
import com.maybankassessment.restservicebackend.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired
    private ProductRepository productRepository;
	
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product productdetails) {
    	Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    	product.setProductName(productdetails.getProductName());
    	product.setProductType(productdetails.getProductType());
        return productRepository.save(product);
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

}
