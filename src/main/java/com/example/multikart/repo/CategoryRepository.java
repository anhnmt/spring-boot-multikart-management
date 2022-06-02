package com.example.multikart.repo;

import com.example.multikart.domain.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAllByStatus(Integer status);

    Category findByCategoryIdAndStatus(Long categoryId, Integer status);

    int countBySlugAndStatus(String slug, Integer status);
}
