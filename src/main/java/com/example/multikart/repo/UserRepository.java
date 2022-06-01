package com.example.multikart.repo;

import com.example.multikart.domain.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAllByStatus(Integer status);

    User findByUserIdAndStatus(Long userId, Integer status);

    User findByEmailAndStatus(String email, Integer status);

    int countByEmailAndStatus(String email, Integer status);
}
