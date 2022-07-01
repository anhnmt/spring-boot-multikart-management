package com.example.multikart.repo;

import com.example.multikart.domain.model.Transport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends CrudRepository<Transport, Long> {
    List<Transport> findAllByStatus(Integer status);

    List<Transport> findAllByStatusNot(Integer status);

    Transport findByTransportIdAndStatus(Long transportId, Integer status);

    Transport findByTransportIdAndStatusNot(Long transportId, Integer status);

    int countByNameAndStatus(String name, Integer status);

    int countByNameAndStatusNot(String name, Integer status);
}
