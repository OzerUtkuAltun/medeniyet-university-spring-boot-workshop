package com.ozerutkualtun.medeniyetdemo.service;

import com.ozerutkualtun.medeniyetdemo.dto.ProductDto;
import com.ozerutkualtun.medeniyetdemo.model.Product;
import com.ozerutkualtun.medeniyetdemo.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // READ
    public ResponseEntity<List<Product>> findProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
    }

    // READ
    public ResponseEntity<Product> findProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalProduct.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // CREATE
    public ResponseEntity<Product> saveProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .expiryDate(productDto.getExpiryDate())
                .isDeleted(false)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    // UPDATE
    public ResponseEntity<Product> updateProduct(Long productId, ProductDto productDto) {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {

            Product product = optionalProduct.get();

            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setExpiryDate(productDto.getExpiryDate());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productRepository.save(product));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE
    public ResponseEntity<Product> deleteProduct(Long productId) {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {

            Product product = optionalProduct.get();
            product.setDeleted(true);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productRepository.save(product));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
