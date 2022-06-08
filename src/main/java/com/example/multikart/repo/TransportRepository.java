package com.example.multikart.repo;

import com.example.multikart.domain.model.Transport;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransportRepository extends CrudRepository<Transport, Long> {
    List<Transport> findAllByStatus(Integer status);

    Transport findByTransportIdAndStatus(Long transportId, Integer status);

    int countByNameAndStatus(String name, Integer status);
}
