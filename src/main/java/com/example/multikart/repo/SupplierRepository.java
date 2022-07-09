package com.example.multikart.repo;

import com.example.multikart.domain.model.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    List<Supplier> findAllByStatus(Integer status);

    List<Supplier> findAllByStatusNot(Integer status);

    Supplier findBySupplierIdAndStatus(Long supplierId, Integer status);

    Supplier findBySupplierIdAndStatusNot(Long supplierId, Integer status);

    int countByNameAndStatus(String name, Integer status);

    int countByNameAndStatusNot(String name, Integer status);
}
