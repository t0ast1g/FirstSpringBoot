package com.elkabani.firstspringboot.repositories;

import com.elkabani.firstspringboot.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}

