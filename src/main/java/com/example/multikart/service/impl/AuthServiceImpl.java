package com.example.multikart.service.impl;

import com.example.multikart.common.Const;
import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.Const.OrderStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.common.Utils;
import com.example.multikart.domain.dto.UserLoginRequestDTO;
import com.example.multikart.domain.dto.UserProfileRequestDTO;
import com.example.multikart.domain.dto.UserRegisterRequestDTO;
import com.example.multikart.domain.model.Customer;
import com.example.multikart.domain.model.ProductImage;
import com.example.multikart.domain.model.User;
import com.example.multikart.repo.CustomerRepository;
import com.example.multikart.repo.OrderRepository;
import com.example.multikart.repo.UserRepository;
import com.example.multikart.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    private String customerDirectory = "uploads/images/customers";
    private String userDirectory = "uploads/images/users";

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

        user.setAvatar(DataUtils.getValueOrDefault(user.getAvatar(), "assets/images/user_image.png"));
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

        user.setAvatar(DataUtils.getValueOrDefault(user.getAvatar(), "assets/images/user_image.png"));
        session.setAttribute("user", user);
        return "redirect:/dashboard/profile";
    }

    @Override
    public String backendPostAvatar(MultipartFile file, HttpSession session, RedirectAttributes redirect) throws IOException {
        var userSession = Utils.getUserSession(session);

        var user = userRepository.findByUserIdAndStatus(userSession.getUserId(), DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(user)) {
            redirect.addFlashAttribute("error", "Tài khoản không tồn tại");

            return "redirect:/";
        }

        var newCus = userRepository.save(user);
        session.setAttribute("user", newCus);

        if (DataUtils.isNullOrEmpty(file)) {
            redirect.addFlashAttribute("error", "Vui lòng chọn file");

            return "redirect:/dashboard/profile";
        }

        Path uploadPath = Paths.get(userDirectory);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = Utils.toSlug(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        String uploadDir = userDirectory + "/" + user.getUserId();

        try {
            Utils.saveFile(uploadDir, fileName, file);

            user.setAvatar(uploadDir + "/" + fileName);
            userRepository.save(user);

            redirect.addFlashAttribute("success", "Cập nhật thành công");
        } catch (IOException e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", "Cập nhật thành công");
        }

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

        cus.setAvatar(DataUtils.getValueOrDefault(cus.getAvatar(), "assets/images/user_image.png"));
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
        newCus.setAvatar(DataUtils.getValueOrDefault(newCus.getAvatar(), "assets/images/user_image.png"));

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

        customer.setAvatar(DataUtils.getValueOrDefault(customer.getAvatar(), "assets/images/user_image.png"));
        model.addAttribute("customer", customer);

        var totalOrders = orderRepository.countByCustomerIdAndStatusNot(customer.getCustomerId(), OrderStatus.DELETED);
        model.addAttribute("totalOrders", totalOrders);

        var ordersPending = orderRepository.countByCustomerIdAndStatus(customer.getCustomerId(), OrderStatus.PENDING);
        model.addAttribute("ordersPending", ordersPending);

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
        newCus.setAvatar(DataUtils.getValueOrDefault(newCus.getAvatar(), "assets/images/user_image.png"));
        session.setAttribute("customer", newCus);

        redirect.addFlashAttribute("success", "Cập nhật thành công");
        return "redirect:/profile";
    }

    @Override
    public String frontendPostAvatar(MultipartFile file, HttpSession session, RedirectAttributes redirect) throws IOException {
        var customerSession = Utils.getCustomerSession(session);

        var customer = customerRepository.findByCustomerIdAndStatus(customerSession.getCustomerId(), DefaultStatus.ACTIVE);
        if (DataUtils.isNullOrEmpty(customer)) {
            redirect.addFlashAttribute("error", "Tài khoản không tồn tại");

            return "redirect:/";
        }

        var newCus = customerRepository.save(customer);
        session.setAttribute("customer", newCus);

        if (DataUtils.isNullOrEmpty(file)) {
            redirect.addFlashAttribute("error", "Vui lòng chọn file");

            return "redirect:/profile";
        }

        Path uploadPath = Paths.get(customerDirectory);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = Utils.toSlug(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        String uploadDir = customerDirectory + "/" + customer.getCustomerId();

        try {
            Utils.saveFile(uploadDir, fileName, file);

            customer.setAvatar(uploadDir + "/" + fileName);
            customerRepository.save(customer);

            redirect.addFlashAttribute("success", "Cập nhật thành công");
        } catch (IOException e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", "Cập nhật thành công");
        }

        return "redirect:/profile";
    }
}
