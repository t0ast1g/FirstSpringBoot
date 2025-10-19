package com.elkabani.firstspringboot.repositories;

import com.elkabani.firstspringboot.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}

