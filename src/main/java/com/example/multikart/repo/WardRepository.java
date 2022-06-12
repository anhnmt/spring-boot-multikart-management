package com.example.multikart.repo;

import com.example.multikart.domain.model.Ward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends CrudRepository<Ward, Long> {
}
