package com.example.multikart.repo;

import com.example.multikart.domain.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findAllByStatus(Integer status);
}