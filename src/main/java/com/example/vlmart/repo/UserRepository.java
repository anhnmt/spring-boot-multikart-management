package com.example.vlmart.repo;

import com.example.vlmart.domain.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByUserId(Long userId);
    Optional<User> findByEmail(String email);
}
