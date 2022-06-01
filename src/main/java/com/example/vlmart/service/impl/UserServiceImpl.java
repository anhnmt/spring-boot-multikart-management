package com.example.vlmart.service.impl;

import com.example.vlmart.common.Const;
import com.example.vlmart.common.Const.DefaultStatus;
import com.example.vlmart.common.DataUtils;
import com.example.vlmart.domain.dto.CreateUserRequestDTO;
import com.example.vlmart.domain.dto.UpdateUserRequestDTO;
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
        var users = userRepository.findAllByStatus(DefaultStatus.ACTIVE);
        model.addAttribute("users", users);

        return "backend/user/index";
    }

    @Override
    public String createUser(Model model) {
        model.addAttribute("user", User.builder().status(DefaultStatus.ACTIVE).build());
        model.addAttribute("roles", roleRepository.findAll());

        return "backend/user/create";
    }

    @Override
    public String storeUser(CreateUserRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("user", input);
            model.addAttribute("roles", roleRepository.findAll());
            return "backend/user/create";
        }

        input.setPassword(bcryptPasswordEncoder.encode(input.getPassword()));
        var count = userRepository.countByEmailAndStatus(input.getEmail(), DefaultStatus.ACTIVE);
        if (count > 0) {
            result.rejectValue("email", "email.required", "Email đã được sử dụng");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", input);
            model.addAttribute("roles", roleRepository.findAll());
            return "backend/user/create";
        }

        var newUser = new User(input);
        userRepository.save(newUser);

        redirect.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/dashboard/users";
    }

    @Override
    public String edit(Long id, Model model, RedirectAttributes redirect) {
        var user = userRepository.findByUserIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(user)) {
            redirect.addFlashAttribute("error", "Người dùng không tồn tại");
            return "redirect:/dashboard/users";
        }

        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());

        return "backend/user/edit";
    }

    @Override
    public String update(Long id, UpdateUserRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        var user = userRepository.findByUserIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(user)) {
            redirect.addFlashAttribute("error", "Người dùng không tồn tại");
            return "redirect:/dashboard/users";
        }

        if (result.hasErrors()) {
            model.addAttribute("user", input);
            model.addAttribute("roles", roleRepository.findAll());
            return "backend/user/create";
        }

        if (!input.getPassword().isEmpty()) {
            user.setPassword(bcryptPasswordEncoder.encode(input.getPassword()));
        }

        if (!user.getEmail().equals(input.getEmail())) {
            var count = userRepository.countByEmailAndStatus(input.getEmail(), DefaultStatus.ACTIVE);
            if (count > 0) {
                result.rejectValue("email", "email.required", "Email đã được sử dụng");
            }

            user.setEmail(input.getEmail());
        }

        if (result.hasErrors()) {
            model.addAttribute("user", input);
            model.addAttribute("roles", roleRepository.findAll());
            return "backend/user/create";
        }

        user.setName(input.getName());
        user.setRoleId(input.getRoleId());
        user.setStatus(input.getStatus());
        userRepository.save(user);

        redirect.addFlashAttribute("success", "Sửa thành công");
        return "redirect:/dashboard/users";
    }

    @Override
    public String deleteUser(Long id, Model model, RedirectAttributes redirect) {
        var user = userRepository.findByUserIdAndStatus(id, DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(user)) {
            redirect.addFlashAttribute("error", "Người dùng không tồn tại");
            return "redirect:/dashboard/users";
        }

        user.setStatus(DefaultStatus.DELETED);
        userRepository.save(user);

        redirect.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/dashboard/users";
    }
}
