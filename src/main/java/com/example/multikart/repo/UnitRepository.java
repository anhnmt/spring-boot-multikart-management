package com.example.multikart.repo;

import com.example.multikart.domain.model.Unit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UnitRepository extends CrudRepository<Unit, Long> {
    List<Unit> findAllByStatus(Integer status);

    Unit findByUnitIdAndStatus(Long categoryId, Integer status);

    int countByNameAndStatus(String name, Integer status);
}
