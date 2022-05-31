package com.example.vlmart.config;

import com.example.vlmart.domain.model.Category;
import com.example.vlmart.domain.model.Role;
import com.example.vlmart.domain.model.Unit;
import com.example.vlmart.domain.model.User;
import com.example.vlmart.repo.CategoryRepository;
import com.example.vlmart.repo.RoleRepository;
import com.example.vlmart.repo.UnitRepository;
import com.example.vlmart.repo.UserRepository;
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
    private BCryptPasswordEncoder bcryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        roleSeeder();
        userSeeder();
        unitSeeder();
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
                    .status(1)
                    .build());
            users.add(User.builder()
                    .email("user@gmail.com")
                    .name("User")
                    .roleId(2L)
                    .password(encodedPassword)
                    .status(1)
                    .build());

            userRepository.saveAll(users);
        }
    }

    private void unitSeeder() {
        var count = unitRepository.count();
        if (count <= 0) {
            List<Unit> units = new ArrayList<>();
            units.add(Unit.builder()
                    .name("Hộp")
                    .build());
            units.add(Unit.builder()
                    .name("Gói")
                    .build());
            units.add(Unit.builder()
                    .name("Khay")
                    .build());
            units.add(Unit.builder()
                    .name("Thùng")
                    .build());
            units.add(Unit.builder()
                    .name("Lon")
                    .build());
            units.add(Unit.builder()
                    .name("Chai")
                    .build());

            unitRepository.saveAll(units);
        }
    }

    private void categorySeeder() {
        var count = categoryRepository.count();
        if (count <= 0) {
            List<Category> categories = new ArrayList<>();
            categories.add(Category.builder()
                    .name("Hộp")
                    .build());

            categoryRepository.saveAll(categories);
        }
    }
}
