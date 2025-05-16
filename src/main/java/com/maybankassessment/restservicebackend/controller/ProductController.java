package com.maybankassessment.restservicebackend.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maybankassessment.restservicebackend.model.Product;
import com.maybankassessment.restservicebackend.service.GetProductDetailsFromAPI;
import com.maybankassessment.restservicebackend.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	 private static final Logger logger = LogManager.getLogger(ProductController.class);
	@Autowired
	ProductService productService;
	@Autowired
	GetProductDetailsFromAPI getProductDetailsFromAPI;
	
	@PostMapping("/createproduct")
    public Product createProduct(@RequestBody Product product) {
		logger.info("request body " +product.toString());
        return productService.createProduct(product);
    }
	  @PutMapping("/updateproduct/{id}")
	    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		  logger.info("request body " +product.toString());
	        return productService.updateProduct(id, product);
	    }
	  @GetMapping("/getproduct/{id}")
	  public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
	      Product product = productService.getProductById(id);
	      if (product != null) {
	          return ResponseEntity.ok(product);
	      } else {
	          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	      }
	  }
	  @GetMapping("/products")
	  public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
		  Pageable fixedPageable = PageRequest.of(pageable.getPageNumber(), 10, pageable.getSort());
	      Page<Product> products = productService.getAllProducts(fixedPageable);
	      return ResponseEntity.ok(products);
	  }
	  @GetMapping("/getproductfromapi/{id}")
	  public ResponseEntity<Product> getDetailsFromOtherApi(@PathVariable("id") Long id) {
	      Product product = getProductDetailsFromAPI.getDetailsFromOtherApi(id);
	      if (product != null) {
	          return ResponseEntity.ok(product);
	      } else {
	          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	      }
	  }
}
