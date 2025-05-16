package com.maybankassessment.restservicebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maybankassessment.restservicebackend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
