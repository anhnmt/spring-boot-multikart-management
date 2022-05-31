package com.example.vlmart.service.impl;

import com.example.vlmart.common.DataUtils;
import com.example.vlmart.domain.dto.CreateUserRequestDTO;
import com.example.vlmart.domain.model.User;
import com.example.vlmart.repo.RoleRepository;
import com.example.vlmart.repo.UserRepository;
import com.example.vlmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String findAllUsers(Model model) {
        var users = userRepository.findAllByStatus(1);
        model.addAttribute("users", users);

        return "backend/user/index";
    }

    @Override
    public String createUser(Model model) {
        model.addAttribute("user", User.builder().status(1).build());
        model.addAttribute("roles", roleRepository.findAll());

        return "backend/user/create";
    }

    @Override
    public String storeUser(@Valid CreateUserRequestDTO input, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", input);
            return "redirect:/dashboard/users/create";
        }

        input.setPassword(bcryptPasswordEncoder.encode(input.getPassword()));
        var count = userRepository.countByEmail(input.getEmail());
        if (count > 0) {
            result.rejectValue("email", "email.required", "Email is already exist");
        }

        if (result.hasErrors()) {
            return "redirect:/dashboard/users/create";
        }

        var newUser = new User(input);
        userRepository.save(newUser);

        return "redirect:/dashboard/users";
    }

    @Override
    public String deleteUser(Long id, BindingResult result, Model model) {
        var user = userRepository.findByUserId(id);
        if (DataUtils.isNullOrEmpty(user)) {
            result.rejectValue("id", "id.notfound", "User is not exist");
        }
        if (user.getStatus() == 0) {
            result.rejectValue("id", "id.deleted", "User was be deleted");
        }
        user.setStatus(0);
        userRepository.save(user);

        return "redirect:/dashboard/users";
    }
}
