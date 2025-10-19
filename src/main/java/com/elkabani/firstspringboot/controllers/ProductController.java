package com.elkabani.firstspringboot.controllers;

import com.elkabani.firstspringboot.entities.Category;
import com.elkabani.firstspringboot.entities.Product;
import com.elkabani.firstspringboot.repositories.CategoryRepository;
import com.elkabani.firstspringboot.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {
        Category category;
        
        // Check if category exists
        if (request.getCategoryId() != null) {
            // Use existing category
            category = categoryRepository.findById(request.getCategoryId()).orElse(null);
            if (category == null) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            // Create new category
            category = Category.builder()
                    .name(request.getCategoryName())
                    .description(request.getCategoryDescription())
                    .build();
            categoryRepository.save(category);
        }

        // Create product
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(category)
                .build();

        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Inner class for request body
    @lombok.Getter
    @lombok.Setter
    public static class ProductRequest {
        private String name;
        private String description;
        private BigDecimal price;
        private Long categoryId; // If provided, use existing category
        private String categoryName; // If categoryId is null, create new category
        private String categoryDescription;
    }
}

