package com.example.multikart.repo;

import com.example.multikart.domain.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findAllByStatus(Integer status);

    List<Role> findAllByStatusNot(Integer status);

    Role findByRoleIdAndStatus(Long roleId, Integer status);

    Role findByRoleIdAndStatusNot(Long roleId, Integer status);

    Role findByRoleId(Long roleId);

    int countByNameAndStatus(String name, Integer status);

    int countByNameAndStatusNot(String name, Integer status);
}
