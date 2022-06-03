package com.example.multikart.repo;

import com.example.multikart.domain.model.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    List<Supplier> findAllByStatus(Integer status);

    Supplier findBySupplierIdAndStatus(Long categoryId, Integer status);

    int countByNameAndStatus(String name, Integer status);
}
