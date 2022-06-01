package com.example.vlmart.service.impl;

import com.example.vlmart.common.DataUtils;
import com.example.vlmart.domain.dto.UserLoginRequestDTO;
import com.example.vlmart.repo.CustomerRepository;
import com.example.vlmart.repo.UserRepository;
import com.example.vlmart.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

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

        var user = userRepository.findByEmailAndStatus(input.getEmail(), 1);
        if (DataUtils.isNullOrEmpty(user)) {
            result.rejectValue("email", null, "Email does not exist");
        }

        if (!bcryptPasswordEncoder.matches(user.getPassword(), input.getPassword())) {
            result.rejectValue("password", null, "Password does not match");
        }

        session.setAttribute("user", user);
        return "redirect:/dashboard";
    }

    @Override
    public String backendLogout(HttpSession session, Model model) {
        session.removeAttribute("user");
        return "redirect:/dashboard/login";
    }

    @Override
    public String frontendLogin(Model model) {
        model.addAttribute("customer", UserLoginRequestDTO.builder()
                .email("cus@gmail.com")
                .password("123456")
                .build());

        return "frontend/auth/login";
    }

    @Override
    public String frontendPostLogin(UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model) {
        log.info("userDTO: {}", input);

        if (result.hasErrors()) {
            return "frontend/auth/login";
        }

        var cus = customerRepository.findByEmail(input.getEmail());
        if (DataUtils.isNullOrEmpty(cus)) {
            result.rejectValue("email", null, "Email does not exist");
        }

        if (!bcryptPasswordEncoder.matches(cus.getPassword(), input.getPassword())) {
            result.rejectValue("password", null, "Password does not match");
        }

        session.setAttribute("customer", cus);
        return "redirect:/";
    }

    @Override
    public String frontendLogout(HttpSession session, Model model) {
        session.removeAttribute("customer");
        return "redirect:/";
    }
}
