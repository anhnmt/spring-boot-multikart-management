package com.example.multikart.repo;

import com.example.multikart.domain.model.Transport;
import org.springframework.data.repository.CrudRepository;

public interface TransportRepository extends CrudRepository<Transport, Long> {
}
