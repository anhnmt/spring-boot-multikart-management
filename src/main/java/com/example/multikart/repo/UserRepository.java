package com.example.multikart.repo;

import com.example.multikart.domain.dto.UserDTO;
import com.example.multikart.domain.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT new com.example.multikart.domain.dto.UserDTO(u, r)\n" +
            "FROM User u\n" +
            "LEFT JOIN Role r on u.roleId = r.roleId\n" +
            "WHERE u.status = :status\n" +
            " AND r.status = :status")
    List<UserDTO> findAllByStatus(Integer status);

    @Query("SELECT new com.example.multikart.domain.dto.UserDTO(u, r)\n" +
            "FROM User u\n" +
            "LEFT JOIN Role r on u.roleId = r.roleId\n" +
            "WHERE u.status <> :status" +
            " AND r.status <> :status")
    List<UserDTO> findAllByStatusNot(Integer status);

    User findByUserIdAndStatus(Long userId, Integer status);

    User findByUserIdAndStatusNot(Long userId, Integer status);

    @Query("SELECT new com.example.multikart.domain.dto.UserDTO(u, r)\n" +
            "FROM User u\n" +
            "LEFT JOIN Role r on u.roleId = r.roleId\n" +
            "WHERE u.status = :status\n" +
            " AND r.status = :status\n" +
            " AND u.email = :email")
    UserDTO findByEmailAndStatus(String email, Integer status);

    int countByEmailAndStatus(String email, Integer status);

    int countByEmailAndStatusNot(String email, Integer status);
}
