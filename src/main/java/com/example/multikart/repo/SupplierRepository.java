package com.example.multikart.repo;

import com.example.multikart.domain.model.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
}
