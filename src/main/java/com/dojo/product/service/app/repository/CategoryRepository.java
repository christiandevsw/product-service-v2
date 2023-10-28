package com.dojo.product.service.app.repository;


import com.dojo.product.service.app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByUniqueIdentifier(String id);
}
