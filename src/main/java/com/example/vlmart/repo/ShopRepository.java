package com.example.vlmart.repo;

import com.example.vlmart.domain.model.Shop;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepository extends CrudRepository<Shop, Long> {
}
