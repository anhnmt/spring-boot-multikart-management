package com.example.multikart.service.impl;

import com.example.multikart.common.Const.DefaultStatus;
import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.dto.CustomerRequestDTO;
import com.example.multikart.domain.model.Customer;
import com.example.multikart.repo.CustomerRepository;
import com.example.multikart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String findAllCustomers(Model model) {
        var customers = customerRepository.findAllByStatusNot(DefaultStatus.DELETED);
        model.addAttribute("customers", customers);

        return "backend/customer/index";
    }

    @Override
    public String createCustomer(Model model) {
        model.addAttribute("customer", Customer.builder().status(DefaultStatus.ACTIVE).build());

        return "backend/customer/create";
    }

    @Override
    public String storeCustomer(CustomerRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("customer", input);

            return "backend/customer/create";
        }

        input.setPassword(bcryptPasswordEncoder.encode(input.getPassword()));
        var count = customerRepository.countByEmailAndStatusNot(input.getEmail().trim(), DefaultStatus.DELETED);
        if (count > 0) {
            result.rejectValue("email", "email.required", "Email đã được sử dụng");
        }

        if (result.hasErrors()) {
            model.addAttribute("customer", input);

            return "backend/customer/create";
        }

        var newCustomer = new Customer(input);
        customerRepository.save(newCustomer);

        redirect.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/dashboard/customers";
    }

    @Override
    public String editCustomer(Long id, Model model, RedirectAttributes redirect) {
        var customer = customerRepository.findByCustomerIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(customer)) {
            redirect.addFlashAttribute("error", "Người dùng không tồn tại");

            return "redirect:/dashboard/customers";
        }

        model.addAttribute("customer", customer);

        return "backend/customer/edit";
    }

    @Override
    public String updateCustomer(Long id, CustomerRequestDTO input, BindingResult result, Model model, RedirectAttributes redirect) {
        var customer = customerRepository.findByCustomerIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(customer)) {
            redirect.addFlashAttribute("error", "Người dùng không tồn tại");

            return "redirect:/dashboard/customers";
        }

        if (result.hasErrors()) {
            model.addAttribute("customer", input);

            return "backend/customer/edit";
        }

        if (!input.getPassword().isEmpty()) {
            customer.setPassword(bcryptPasswordEncoder.encode(input.getPassword()));
        }

        if (!customer.getEmail().equals(input.getEmail())) {
            var count = customerRepository.countByEmailAndStatusNot(input.getEmail().trim(), DefaultStatus.DELETED);
            if (count > 0) {
                result.rejectValue("email", "email.required", "Email đã được sử dụng");
            }

            customer.setEmail(input.getEmail());
        }

        if (result.hasErrors()) {
            model.addAttribute("customer", input);

            return "backend/customer/edit";
        }

        customer.setName(input.getName());
        customer.setStatus(input.getStatus());
        customerRepository.save(customer);

        redirect.addFlashAttribute("success", "Sửa thành công");
        return "redirect:/dashboard/customers";
    }

    @Override
    public String deleteCustomer(Long id, Model model, RedirectAttributes redirect) {
        var customer = customerRepository.findByCustomerIdAndStatusNot(id, DefaultStatus.DELETED);
        if (DataUtils.isNullOrEmpty(customer)) {
            redirect.addFlashAttribute("error", "Người dùng không tồn tại");

            return "redirect:/dashboard/customers";
        }

        customer.setStatus(DefaultStatus.DELETED);
        customerRepository.save(customer);

        redirect.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/dashboard/customers";
    }
}
