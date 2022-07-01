package com.example.multikart.repo;

import com.example.multikart.domain.model.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Long> {
    List<Unit> findAllByStatus(Integer status);

    List<Unit> findAllByStatusNot(Integer status);

    Unit findByUnitIdAndStatus(Long unitId, Integer status);

    Unit findByUnitIdAndStatusNot(Long unitId, Integer status);

    int countByNameAndStatus(String name, Integer status);

    int countByNameAndStatusNot(String name, Integer status);
}
