package com.example.multikart.repo;

import com.example.multikart.domain.model.Category;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAllByStatus(Integer status);
    List<Category> findAllByStatusNot(Integer status);

    Category findByCategoryIdAndStatus(Long categoryId, Integer status);
    Category findByCategoryIdAndStatusNot(Long categoryId, Integer status);

    Category findBySlugAndStatus(String slug, Integer status);
    int findBySlugAndStatusNot(String slug, Integer status);

    int countBySlugAndStatus(String slug, Integer status);
}
