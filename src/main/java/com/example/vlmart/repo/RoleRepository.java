package com.example.vlmart.repo;

import com.example.vlmart.domain.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findAll();
}
