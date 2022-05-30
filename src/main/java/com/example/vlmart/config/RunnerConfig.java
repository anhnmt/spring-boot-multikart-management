package com.example.vlmart.config;

import com.example.vlmart.domain.model.Role;
import com.example.vlmart.domain.model.User;
import com.example.vlmart.repo.RoleRepository;
import com.example.vlmart.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RunnerConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        roleSeeder();
        userSeeder();
    }

    private void roleSeeder() {
        var count = roleRepository.count();
        if (count <= 0) {
            List<Role> roles = new ArrayList<>();
            roles.add(Role.builder()
                    .name("ROLE_ADMIN")
                    .build());
            roles.add(Role.builder()
                    .name("ROLE_USER")
                    .build());

           roleRepository.saveAll(roles);
        }
    }

    private void userSeeder() {
        String encodedPassword = bcryptPasswordEncoder.encode("123456");

        var count = userRepository.count();
        if (count <= 0) {
            List<User> users = new ArrayList<>();
            users.add(User.builder()
                    .email("admin@gmail.com")
                    .name("Admin")
                    .roleId(1L)
                    .password(encodedPassword)
                    .build());
            users.add(User.builder()
                    .email("user@gmail.com")
                    .name("User")
                    .roleId(2L)
                    .password(encodedPassword)
                    .build());

            userRepository.saveAll(users);
        }
    }
}
