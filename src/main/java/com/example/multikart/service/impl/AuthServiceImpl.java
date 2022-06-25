package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.common.Utils;
import com.example.multikart.domain.dto.UserLoginRequestDTO;
import com.example.multikart.domain.dto.UserProfileRequestDTO;
import com.example.multikart.domain.dto.UserRegisterRequestDTO;
import com.example.multikart.domain.model.Customer;
import com.example.multikart.domain.model.User;
import com.example.multikart.repo.CustomerRepository;
import com.example.multikart.repo.UserRepository;
import com.example.multikart.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("user", UserLoginRequestDTO.builder().email("admin@gmail.com").password("123456").build());

        return "backend/auth/login";
    }

    @Override
    public String backendPostLogin(String referer, UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model) {
        log.info("userDTO: {}", input);

        if (result.hasErrors()) {
            model.addAttribute("user", input);
            return "backend/auth/login";
        }

        var user = userRepository.findByEmailAndStatus(input.getEmail(), DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(user)) {
            result.rejectValue("email", "", "Email không tồn tại");
        }

        if (!bcryptPasswordEncoder.matches(input.getPassword(), user.getPassword())) {
            result.rejectValue("password", "", "Mật khẩu không khớp");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", input);
            return "backend/auth/login";
        }

        session.setAttribute("user", user);

        if (!DataUtils.isNullOrEmpty(referer)) {
            return "redirect:" + referer;
        }

        return "redirect:/dashboard";
    }

    @Override
    public String backendLogout(HttpSession session, Model model) {
        session.removeAttribute("user");
        return "redirect:/dashboard/login";
    }

    @Override
    public String backendProfile(HttpSession session, Model model) {
        var user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "backend/profile";
    }

    @Override
    public String backendPostProfile(UserProfileRequestDTO input, HttpSession session, BindingResult result, Model model, RedirectAttributes redirect) {
        var userSession = (User) session.getAttribute("user");

        var user = userRepository.findByUserIdAndStatus(userSession.getUserId(), DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(user)) {
            redirect.addFlashAttribute("error", "Người dùng không tồn tại");

            return "redirect:/dashboard/profile";
        }

        if (result.hasErrors()) {
            model.addAttribute("user", input);

            return "backend/profile";
        }

        if (!input.getPassword().isEmpty()) {
            user.setPassword(bcryptPasswordEncoder.encode(input.getPassword()));
        }

        if (!user.getEmail().equals(input.getEmail())) {
            var count = userRepository.countByEmailAndStatus(input.getEmail(), DefaultStatus.ACTIVE);
            if (count > 0) {
                result.rejectValue("email", "", "Email đã được sử dụng");
            }

            user.setEmail(input.getEmail());
        }

        if (result.hasErrors()) {
            model.addAttribute("user", input);

            return "backend/profile";
        }

        user.setName(input.getName());
        userRepository.save(user);

        redirect.addFlashAttribute("success", "Sửa thành công");

        session.setAttribute("user", user);
        return "redirect:/dashboard/profile";
    }

    @Override
    public String frontendLogin(Model model) {
        model.addAttribute("customer", UserLoginRequestDTO.builder().email("cus@gmail.com").password("123456").build());

        return "frontend/auth/login";
    }

    @Override
    public String frontendPostLogin(String referer, UserLoginRequestDTO input, HttpSession session, BindingResult result, Model model) {
        log.info("userDTO: {}", input);

        if (result.hasErrors()) {
            model.addAttribute("customer", input);
            return "frontend/auth/login";
        }

        var cus = customerRepository.findByEmailAndStatus(input.getEmail(), DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(cus)) {
            result.rejectValue("email", "", "Email không tồn tại");
        }

        if (!bcryptPasswordEncoder.matches(input.getPassword(), cus.getPassword())) {
            result.rejectValue("password", "", "Mật khẩu không khớp");
        }

        if (result.hasErrors()) {
            model.addAttribute("customer", input);
            return "frontend/auth/login";
        }

        session.setAttribute("customer", cus);

        if (!DataUtils.isNullOrEmpty(referer)) {
            return "redirect:" + referer;
        }

        return "redirect:/";
    }

    @Override
    public String frontendLogout(HttpSession session, Model model) {
        session.removeAttribute("customer");
        return "redirect:/";
    }

    @Override
    public String frontendRegister(Model model) {
        model.addAttribute("customer", UserRegisterRequestDTO.builder().name("Tim Văn Cúc").email("demo@gmail.com").password("123456").confirmPassword("123456").build());

        return "frontend/auth/register";
    }

    @Override
    public String frontendPostRegister(UserRegisterRequestDTO input, HttpSession session, BindingResult result, Model model) {
        log.info("userDTO: {}", input);

        if (result.hasErrors()) {
            model.addAttribute("customer", input);
            return "frontend/auth/register";
        }

        var cus = customerRepository.findByEmailAndStatus(input.getEmail(), DefaultStatus.ACTIVE);
        if (!DataUtils.isNullOrEmpty(cus)) {
            result.rejectValue("email", "", "Email đã tồn tại");
        }

        if (!input.getPassword().equalsIgnoreCase(input.getConfirmPassword())) {
            result.rejectValue("password", "", "Mật khẩu không khớp");
        }

        if (result.hasErrors()) {
            model.addAttribute("customer", input);
            return "frontend/auth/register";
        }

        var newCus = customerRepository.save(new Customer(input));

        session.setAttribute("customer", newCus);
        return "redirect:/";
    }

    @Override
    public String frontendProfile(HttpSession session, Model model, RedirectAttributes redirect) {
        var customerSession = Utils.getCustomerSession(session);

        var customer = customerRepository.findByCustomerIdAndStatus(customerSession.getCustomerId(), DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(customer)) {
            redirect.addFlashAttribute("error", "Tài khoản không tồn tại");

            return "redirect:/";
        }

        model.addAttribute("customer", customer);

        return "frontend/profile";
    }

    @Override
    public String frontendPostProfile(UserProfileRequestDTO input, HttpSession session, BindingResult result, Model model, RedirectAttributes redirect) {
        var customerSession = Utils.getCustomerSession(session);

        var customer = customerRepository.findByCustomerIdAndStatus(customerSession.getCustomerId(), DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(customer)) {
            redirect.addFlashAttribute("error", "Tài khoản không tồn tại");

            return "redirect:/";
        }

        if (result.hasErrors()) {
            model.addAttribute("customer", input);
            return "frontend/profile";
        }

        if (!input.getPassword().isEmpty()) {
            customer.setPassword(bcryptPasswordEncoder.encode(input.getPassword()));
        }

        if (!customer.getEmail().equals(input.getEmail())) {
            var count = userRepository.countByEmailAndStatus(input.getEmail(), DefaultStatus.ACTIVE);
            if (count > 0) {
                result.rejectValue("email", "", "Email đã được sử dụng");
            }

            customer.setEmail(input.getEmail());
        }

        if (result.hasErrors()) {
            model.addAttribute("user", input);

            return "frontend/profile";
        }

        customer.setName(input.getName());
        customer.setPhone(input.getPhone());
        customer.setAddress(input.getAddress());
        customer.setProvinceId(input.getProvinceId());
        customer.setDistrictId(input.getDistrictId());
        customer.setWardId(input.getWardId());

        var newCus = customerRepository.save(customer);
        session.setAttribute("customer", newCus);

        redirect.addFlashAttribute("success", "Cập nhật thành công");
        return "redirect:/profile";
    }
}
