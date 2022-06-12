package com.example.multikart.repo;

import com.example.multikart.domain.model.District;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends CrudRepository<District, Long> {
}
