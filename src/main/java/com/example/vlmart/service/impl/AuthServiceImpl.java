package com.example.vlmart.service.impl;

import com.example.vlmart.common.DataUtils;
import com.example.vlmart.domain.dto.UserLoginRequestDTO;
import com.example.vlmart.repo.UserRepository;
import com.example.vlmart.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String backendLogin(Model model) {
        model.addAttribute("user", UserLoginRequestDTO.builder()
                .email("admin@gmail.com")
                .password("123456")
                .build());

        return "backend/auth/login";
    }

    @Override
    public String backendPostLogin(@Valid UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model) {
        log.info("userDTO: {}", input);

        if (result.hasErrors()) {
            return "backend/auth/login";
        }

        var user = userRepository.findByEmail(input.getEmail());
        if (DataUtils.isNullOrEmpty(user)) {
            result.rejectValue("email", null, "Email does not exist");
        }

        if (!user.getPassword().equals(input.getPassword())) {
            result.rejectValue("password", null, "Password does not match");
        }

        session.setAttribute("user", user);
        return "redirect:/dashboard";
    }

    @Override
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        return "redirect:/dashboard/login";
    }
}
